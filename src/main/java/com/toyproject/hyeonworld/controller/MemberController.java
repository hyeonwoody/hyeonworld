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
        public void send (String eventName){ //WaintingList
            DataMap dataMap = new DataMap();
            List<String> waitngList = memberService.getWaitingList();
            send (eventName, dataMap.mapOf("waitingList", waitngList));
        }

        public void send (String eventName, String memberName){ //AdditionalWaitingList
            DataMap dataMap = new DataMap();
            send (eventName, dataMap.mapOf("memberName", memberName));
        }


    }

    private final WaitingListEmitters sseEmitters = new WaitingListEmitters();

    public MemberController(MemberService memberService, ThreadService threadService) {
        this.memberService = memberService;
        this.threadService = threadService;
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
        CustomSseEmitter emitter = new CustomSseEmitter ();
        sseEmitters.add(emitter);
        sseEmitters.send ("RemoveWaitingList", enterMemeberName);
        return ResponseEntity.ok (memberId);
    }

    @PostMapping("/exit-game")
    public ResponseEntity<Long> exitGame (@RequestParam Long memberId){
        String enterMemberName = memberService.exitGame_String(memberId);
        sseEmitters.send ("AddWaitingList", enterMemberName);
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
        System.out.println("Waiting List bb");

        /*
        Init Waiting List.
         */
        CustomSseEmitter emitter = new CustomSseEmitter ();
        sseEmitters.add(emitter);

        return ResponseEntity.ok(emitter);
    }
}
