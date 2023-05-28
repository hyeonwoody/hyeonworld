package com.toyproject.hyeonworld.controller;

import com.toyproject.hyeonworld.controller.sse.CustomSseEmitter;
import com.toyproject.hyeonworld.controller.sse.DataMap;
import com.toyproject.hyeonworld.controller.sse.SseEmitters;
import com.toyproject.hyeonworld.entity.Game;
import com.toyproject.hyeonworld.service.MemberService;
import com.toyproject.hyeonworld.service.ThreadService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final ThreadService threadService;

    private static final long SSE_SESSION_TIMEOUT = 3500L;

    private class WaitingListEmitters extends SseEmitters {
        @Override
        public void send (String eventName){
            DataMap dataMap = new DataMap();
            List<String> waitngList = memberService.getWaitingList();
            send (eventName, dataMap.mapOf("waitingList", waitngList));
        }

        public void send (String eventName, String memberName){
            DataMap dataMap = new DataMap();
            send (eventName, dataMap.mapOf("additionalList", memberName));
        }
    }

    private final WaitingListEmitters sseEmitters = new WaitingListEmitters();

    public MemberController(MemberService memberService, ThreadService threadService) {
        this.memberService = memberService;
        this.threadService = threadService;
    }


    @PutMapping("/login-confirm")
    public ResponseEntity<Long> loginConfirm (@RequestParam String loginName){

        Future< Long> futureResult =  threadService.executorService.submit(()->{
            return memberService.login (loginName);
        });

        try {
            Long loginMemberId  = futureResult.get();
            return ResponseEntity.ok (loginMemberId);
        }catch (InterruptedException | ExecutionException e ){
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/logout-confirm")
    public ResponseEntity<Long> logoutConfirm (@RequestParam Long logoutId){

        Future< Long> futureResult = threadService.executorService.submit(()->{
            return memberService.logout (logoutId);
        });

        try {
            Long loginMemberId  = futureResult.get();
            return ResponseEntity.ok (loginMemberId);
        }catch (InterruptedException | ExecutionException e ){
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/enter-game")
    public ResponseEntity<Long> enterGame (@RequestParam Long memberId){
        String enterMemeberName = memberService.enterGame_String(memberId);
        sseEmitters.send ("WaitingList", enterMemeberName);
        return ResponseEntity.ok (memberId);
    }

    @PostMapping("/exit-game")
    public ResponseEntity<Long> exitGame (@RequestParam Long memberId){
        Long enterMemberId = memberService.exitGame(memberId);
        return ResponseEntity.ok (enterMemberId);
    }

    @GetMapping(value = "/waiting-list", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<CustomSseEmitter> waitingList (@RequestParam Long memberId){
        System.out.println("Waiting List ");
        CustomSseEmitter emitter = new CustomSseEmitter ();
        sseEmitters.add(emitter);
        return ResponseEntity.ok(emitter);
    }
}
