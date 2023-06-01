package com.toyproject.hyeonworld.controller;

import com.toyproject.hyeonworld.controller.sse.CustomSseEmitter;
import com.toyproject.hyeonworld.controller.sse.SseEmitters;

import com.toyproject.hyeonworld.service.MemberService;
import com.toyproject.hyeonworld.service.ThreadService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
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
    public ResponseEntity<Long> init(){
        System.out.println("INIT MEMBER");
        Long ret = memberService.init ();
        return ResponseEntity.ok (ret);
    }

    @PutMapping("/login-confirm")
    public ResponseEntity<Long> loginConfirm (@RequestParam String loginName){
        Long loginMemberId  = memberService.login (loginName);
        if (loginMemberId > 0 && !sseEmitters.empty())
            sseEmitters.send ("AddWaitingList", loginName);
        return ResponseEntity.ok (loginMemberId);
    }

    @PutMapping("/logout-confirm")
    public ResponseEntity<Long> logoutConfirm (@RequestParam Long logoutId){
        Long loginMemberId = memberService.logout (logoutId);
        return ResponseEntity.ok (loginMemberId);
    }

    @PostMapping("/enter-game")
    public ResponseEntity<Long> enterGame (@RequestBody HashMap<String, Long> request){

        String enterMemeberName = memberService.enterGame_String(request.get("memberId"));

        if (request.get("memberId") > 0 && !sseEmitters.empty())
            sseEmitters.send ("RemoveWaitingList", enterMemeberName);
        return ResponseEntity.ok (request.get("memberId"));
    }

    @PostMapping("/exit-game")
    public ResponseEntity<Long> exitGame (@RequestBody HashMap<String, Long> request){

        String exitMemberName = memberService.exitGame_String( request.get("memberId"));

        if ( request.get("memberId") > 0 && !sseEmitters.empty())
            sseEmitters.send ("AddWaitingList", exitMemberName);

        return ResponseEntity.ok (request.get("memberId"));
    }

    @GetMapping(value = "/waiting-list/init")
    public ResponseEntity<List<String>> WaitingListInit (@RequestParam Long memberId){
        List<String> waitngList = memberService.getWaitingList();
        System.out.println("Waiting List aaa");
        return ResponseEntity.ok (waitngList);
    }
}
