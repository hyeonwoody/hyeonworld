package com.toyproject.hyeonworld.controller;

import com.toyproject.hyeonworld.entity.Round;
import com.toyproject.hyeonworld.service.RoundService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/round")
public class RoundController {

    private final RoundService roundService;

    private static List<String> game0NameList;
    private static boolean game0Init = false;
    private static Map<String, Object> game0Ranking = new HashMap<String, Object>();

    private static int currentRound = 0;

    public RoundController(RoundService roundService) {
        this.roundService = roundService;
    }

    @PostMapping("/0")
    public ResponseEntity<Boolean> postRound0 (@RequestBody HashMap<String, String > request){

        Round round = roundService.postRound0(currentRound, request.get("memberName"));
        System.out.println("ROUND Controller"+currentRound);
        return ResponseEntity.ok (true);
    }

    @GetMapping("/0")
    public ResponseEntity<List<String>> getRound0 (){
        System.out.println("트레이드");
        if (!game0Init){
            System.out.println("GAME Init");
            game0NameList = roundService.get0List(currentRound);
            game0Init = true;

        }

        return ResponseEntity.ok (game0NameList);
    }

    @PutMapping("/0")
    public ResponseEntity<Boolean> getRanking0 (){
        game0Init = false;
        ++currentRound;
        return ResponseEntity.ok (true);
    }
}
