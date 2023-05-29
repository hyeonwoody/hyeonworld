package com.toyproject.hyeonworld.controller;

import com.toyproject.hyeonworld.controller.sse.CustomSseEmitter;
import com.toyproject.hyeonworld.controller.sse.SseEmitters;

import com.toyproject.hyeonworld.service.MemberService;
import com.toyproject.hyeonworld.service.ThreadService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;



    private final SseEmitters sseEmitters;

    public MemberController(MemberService memberService, SseEmitters sseEmitters) {
        this.memberService = memberService;
        this.sseEmitters = sseEmitters;
    }
    @PutMapping("/init")
    public ResponseEntity<Long> initMember(){
        System.out.println("INIT MEMBER");
        Long ret = memberService.init ();
        return ResponseEntity.ok (ret);
    }

    @PutMapping("/login-confirm")
    public ResponseEntity<Long> loginConfirm (@RequestParam String loginName){
        Long loginMemberId  = memberService.login (loginName);
        if (loginMemberId != -1L && !sseEmitters.empty())
            sseEmitters.send ("AddWaitingList", loginName);
        return ResponseEntity.ok (loginMemberId);
    }

    @PutMapping("/logout-confirm")
    public ResponseEntity<Long> logoutConfirm (@RequestParam Long logoutId){
        Long loginMemberId = memberService.logout (logoutId);
        return ResponseEntity.ok (loginMemberId);
    }

    @PostMapping("/enter-game")
    public ResponseEntity<Long> enterGame (@RequestParam Long memberId){
        String enterMemeberName = memberService.enterGame_String(memberId);
        System.out.println("Enter Game 길이 : "+sseEmitters.size());
        if (!sseEmitters.empty())
            sseEmitters.send ("RemoveWaitingList", enterMemeberName);
        return ResponseEntity.ok (memberId);
    }

    @PostMapping("/exit-game")
    public ResponseEntity<Long> exitGame (@RequestParam Long memberId){
        String enterMemberName = memberService.exitGame_String(memberId);

        if (!sseEmitters.empty()) {
            sseEmitters.send ("AddWaitingList", enterMemberName);
        }

        return ResponseEntity.ok (memberId);
    }

    @GetMapping(value = "/waiting-list/init")
    public ResponseEntity<List<String>> initWaitingList (@RequestParam Long memberId){
        List<String> waitngList = memberService.getWaitingList();
        System.out.println("Waiting List aaa");
        return ResponseEntity.ok (waitngList);
    }

    @GetMapping(value = "/waiting-list/additional", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<CustomSseEmitter> waitingList (@RequestParam Long memberId){
        System.out.println("Waiting List bb"+ memberId);

        /*
        Init Waiting List.
         */
        CustomSseEmitter emitter = new CustomSseEmitter (memberId, "AddWaitingList");
        System.out.println("Waiting List Additional 길이 : "+sseEmitters.size());

        return ResponseEntity.ok(emitter);
    }
}
