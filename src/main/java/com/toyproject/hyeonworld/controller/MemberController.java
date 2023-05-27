package com.toyproject.hyeonworld.controller;

import com.toyproject.hyeonworld.controller.sse.CustomSseEmitter;
import com.toyproject.hyeonworld.controller.sse.SseEmitters;
import com.toyproject.hyeonworld.service.MemberService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    private static final long SSE_SESSION_TIMEOUT = 3500L;

    private final SseEmitters sseEmitters;
    public MemberController(MemberService memberService, SseEmitters sseEmitters) {
        this.memberService = memberService;
        this.sseEmitters = sseEmitters;
    }


    @PutMapping("/login-confirm")
    public ResponseEntity<Long> loginConfirm (@RequestParam String loginName){
        Long loginMemberId = memberService.login (loginName);
        return ResponseEntity.ok (loginMemberId);
    }

    @PutMapping("/logout-confirm")
    public ResponseEntity<Long> logoutConfirm (@RequestParam Long logoutId){
        Long logoutMemberId = memberService.logout (logoutId);
        return ResponseEntity.ok (logoutMemberId);
    }

    @PostMapping("/enter-game")
    public ResponseEntity<Long> enterGame (@RequestParam Long memberId){
        Long enterMemberId = memberService.enterGame(memberId);
        return ResponseEntity.ok (enterMemberId);
    }

    @PostMapping("/exit-game")
    public ResponseEntity<Long> exitGame (@RequestParam Long memberId){
        Long enterMemberId = memberService.exitGame(memberId);
        return ResponseEntity.ok (enterMemberId);
    }

    @GetMapping(value = "/waiting-list", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<CustomSseEmitter> waitingList (@RequestParam Long memberId){
        System.out.println("Waiting List ");
        CustomSseEmitter emitter = new CustomSseEmitter (SSE_SESSION_TIMEOUT);
        sseEmitters.add(emitter);
        sseEmitters.send ("WaitingList");
        return ResponseEntity.ok(emitter);
    }
}
