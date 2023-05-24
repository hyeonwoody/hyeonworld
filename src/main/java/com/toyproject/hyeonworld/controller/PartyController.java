package com.toyproject.hyeonworld.controller;

import com.toyproject.hyeonworld.entity.Party;
import com.toyproject.hyeonworld.service.PartyService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/party")
public class PartyController {

    private final PartyService partyService;

    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }


    @GetMapping("/init")
    public ResponseEntity<Boolean> initParty (HttpServletRequest request, @RequestParam Integer partyType, @RequestParam Integer persons){
        System.out.println("party init");
        Party party =new Party();

        party.setPartyType(partyType);
        party.setPersons(persons);
        party.setCurrentGame(-1);

        partyService.init(party);

        return ResponseEntity.ok (true);
    }

}