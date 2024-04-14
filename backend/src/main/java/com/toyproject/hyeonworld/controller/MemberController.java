package com.toyproject.hyeonworld.controller;

import com.toyproject.hyeonworld.DTO.Member.MemberAnswer;
import com.toyproject.hyeonworld.DTO.Member.MemberScore;
import com.toyproject.hyeonworld.controller.sse.SseEmitters;

import com.toyproject.hyeonworld.entity.Member;
import com.toyproject.hyeonworld.service.MemberService;
import com.toyproject.hyeonworld.service.RoundService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final RoundService roundService;

    private final SseEmitters sseEmitters;

    public MemberController(MemberService memberService, RoundService roundService, SseEmitters sseEmitters) {
        this.memberService = memberService;
        this.roundService = roundService;
        this.sseEmitters = sseEmitters;
    }
    @PutMapping("/init")
    public ResponseEntity<Long> init(){
        System.out.println("INIT MEMBER");
        Long ret = memberService.init ();
        return ResponseEntity.ok (ret);
    }

    @PostMapping("/login-confirm")
    public ResponseEntity<Long> loginConfirm (HttpServletRequest request){
        System.out.println(request.getRemoteAddr());
        String requestBody = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            requestBody = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Long loginMemberId  = memberService.login (requestBody);
        if (loginMemberId > 0 && !sseEmitters.empty())
            sseEmitters.send ("AddWaitingList", requestBody);
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

    @PutMapping("/play/0")
    public ResponseEntity<Long> putPlayGame0 (@RequestBody MemberAnswer memberAnswer){

        Long playMemberId = memberService.putPlayGame0 (memberAnswer);

        return ResponseEntity.ok (playMemberId);
    }

    @PutMapping("/score/0")
    public ResponseEntity<Long> putScore0 (@RequestBody HashMap<String, Long> request){
        System.out.println(" 스코어 "+request.get("correct"));
        List<Member> member = memberService.putScore (0, request);
        return ResponseEntity.ok (request.get("score"));
    }

    @GetMapping("/ranking")
    public ResponseEntity<HashMap<String, List<MemberScore>>> getRanking (){
        HashMap<String, List<MemberScore>> ret = new HashMap<>();
        ret = memberService.getRanking ();

        return ResponseEntity.ok (ret);
    }
}
