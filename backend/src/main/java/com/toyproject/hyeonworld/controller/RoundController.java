package com.toyproject.hyeonworld.controller;

import com.toyproject.hyeonworld.entity.Round;
import com.toyproject.hyeonworld.service.RoundService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/round")
public class RoundController {

    private final RoundService roundService;

    private static boolean game0Init = false;

    private static int currentRound = 0;

    public RoundController(RoundService roundService) {
        this.roundService = roundService;
    }

    @PostMapping("/0")
    public ResponseEntity<Boolean> postRound0 (@RequestBody HashMap<String, String > request){

        roundService.postRound0(request.get("memberName"));
        System.out.println("ROUND Controller"+currentRound);
        return ResponseEntity.ok (true);
    }

    @GetMapping("/0")
    public ResponseEntity<Map<String, Object>> getCorrectList (){

        Map <String, Object> response = new HashMap<>();
            response = roundService.get0List();

        return ResponseEntity.ok (response);
    }

    @PutMapping("/0")
    public ResponseEntity<Boolean> createNewRound (){
        roundService.createNewRound();
        return ResponseEntity.ok (true);
    }
}
