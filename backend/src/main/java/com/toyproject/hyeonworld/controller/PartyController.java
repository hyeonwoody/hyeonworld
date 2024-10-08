package com.toyproject.hyeonworld.controller;

import com.toyproject.hyeonworld.DTO.Game.CurrentGameDTO;
import com.toyproject.hyeonworld.DTO.Member.MemberDTO;
import com.toyproject.hyeonworld.DTO.PartyInitDTO;
import com.toyproject.hyeonworld.DTO.Submission.SubmissionVO;
import com.toyproject.hyeonworld.service.MemberService;
import com.toyproject.hyeonworld.service.PartyService;
import com.toyproject.hyeonworld.service.RoundService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public class PartyController {

    private final PartyService partyService;
    private final MemberService memberService;


    public PartyController(PartyService partyService, MemberService memberService) {
        this.partyService = partyService;
        this.memberService = memberService;
    }

    @PutMapping("/current-game")
    public ResponseEntity<Boolean> openGame (@RequestBody CurrentGameDTO gameDTO){
        partyService.open(gameDTO.getId());

        return ResponseEntity.ok (true);
    }

    @PutMapping("/target")
    public ResponseEntity<Boolean> putTarget (@RequestBody MemberDTO memberDTO){

        partyService.putTarget(memberDTO.getName());
        return ResponseEntity.ok (true);
    }

    @PostMapping("/init")
    public ResponseEntity<Boolean> initParty (@RequestBody PartyInitDTO partyInitDTO){
        System.out.println("party init");
        partyService.init(partyInitDTO);
        memberService.init();
        return ResponseEntity.ok (true);
    }

    @GetMapping("/current-game")
    public ResponseEntity<Integer> getCurrentGame (){

        System.out.println("send current-game");



            Integer currentGame = partyService.getCurrentGameQuery();
            System.out.println("CURRENTGAME VALUE : "+currentGame);
            return ResponseEntity.ok(currentGame);



//        System.out.println("HTTP Method: " + request.getMethod());
//        System.out.println("Request URI: " + request.getRequestURI());
//
//        // Print headers
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            String headerValue = request.getHeader(headerName);
//            System.out.println("Header: " + headerName + " = " + headerValue);
//        }
//
//        // Print parameters
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
//            String paramName = entry.getKey();
//            String[] paramValues = entry.getValue();
//            System.out.println("Parameter: " + paramName + " = " + Arrays.toString(paramValues));
//        }
//
//        // Print other details as needed
//
//        // Example: Print Remote Address
//        System.out.println("Remote Address: " + request.getRemoteAddr());
//        System.out.println("Source Port: " + request.getRemotePort());


    }
    @GetMapping("/target")
    public ResponseEntity<SubmissionVO> getTarget (){

        SubmissionVO submissionVO = partyService.getTarget();

        return ResponseEntity.ok (submissionVO);
    }


}