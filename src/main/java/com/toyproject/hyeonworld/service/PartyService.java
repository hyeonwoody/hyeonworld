package com.toyproject.hyeonworld.service;

import com.toyproject.hyeonworld.entity.Game;
import com.toyproject.hyeonworld.entity.Party;
import com.toyproject.hyeonworld.repository.PartyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PartyService {

    private final PartyRepository partyRepository;

    public PartyService(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    public void init(Party party) {
        partyRepository.save(party);
    }

    public void open(Integer game) {

        Optional<Party> party = partyRepository.findOne();
        if (party.isPresent()){
            Party pParty = party.get();
            pParty.setCurrentGame(game);
            partyRepository.save(pParty);
        }
        System.out.println("DFFFFF");
    }

    public Integer getCurrentGame() {
        return partyRepository.getCurrentGame();
    }
}
