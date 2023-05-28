package com.toyproject.hyeonworld.controller;

import com.toyproject.hyeonworld.entity.Party;
import com.toyproject.hyeonworld.service.PartyService;
import com.toyproject.hyeonworld.service.ThreadService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/party")
public class PartyController {

    private final PartyService partyService;
    private final ThreadService threadService;

    public PartyController(PartyService partyService, ThreadService threadService) {
        this.partyService = partyService;
        this.threadService = threadService;
    }

    @PutMapping("/current-game")
    public ResponseEntity<Boolean> openGame (HttpServletRequest request, @RequestParam Integer game){
        System.out.println("왔ㄷ자");
        partyService.open(game);

        return ResponseEntity.ok (true);
    }

    @PostMapping("/init")
    public ResponseEntity<Boolean> initParty (HttpServletRequest request, @RequestParam Integer partyType, @RequestParam Integer persons){
        System.out.println("party init");
        Party party =new Party();

        threadService.setPoolSize(persons);

        party.setPartyType(partyType);
        party.setPersons(persons);
        party.setCurrentGame(-1);
        party.setCurrentGameStage(0);

        partyService.init(party);

        return ResponseEntity.ok (true);
    }

    @GetMapping("/current-game")
    public ResponseEntity<Integer> getCurrentGame (HttpServletRequest request){

        System.out.println("send current-game");


        Future <Integer> futureResult = threadService.executorService.submit(()->{
            return partyService.getCurrentGameQuery();
        });

        try {
            Integer currentGame = futureResult.get();
            System.out.println("CURRENTGAME VALUE : "+currentGame);
            return ResponseEntity.ok(currentGame);
        }catch (InterruptedException | ExecutionException e ){
            return ResponseEntity.status(500).build();
        }


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



}