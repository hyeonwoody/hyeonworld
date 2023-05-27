package com.toyproject.hyeonworld.controller;

import com.toyproject.hyeonworld.entity.Party;
import com.toyproject.hyeonworld.service.PartyService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/party")
public class PartyController {

    private final PartyService partyService;

    public PartyController(PartyService partyService) {
        this.partyService = partyService;
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
        Integer currentGame = partyService.getCurrentGameQuery();

        return ResponseEntity.ok (currentGame);
    }



}