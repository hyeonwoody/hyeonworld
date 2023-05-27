package com.toyproject.hyeonworld.service;

import com.toyproject.hyeonworld.entity.Game;
import com.toyproject.hyeonworld.entity.Member;
import com.toyproject.hyeonworld.entity.Party;
import com.toyproject.hyeonworld.repository.PartyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        Optional<Party> party = partyRepository.findRecentOne();
        if (party.isPresent()){
            Party pParty = party.get();
            pParty.setCurrentGame(game);
            partyRepository.save(pParty);
        }
    }

    public Integer getCurrentGameQuery() {
        return partyRepository.getCurrentGame();
    }

    public Integer setCurrentGameStage(Integer gameStage) {
        Optional<Party> party = partyRepository.findRecentOne();
        if (party.isPresent()){
            Party pParty = party.get();
            pParty.setCurrentGameStage(gameStage);
            partyRepository.save(pParty);
            return gameStage;
        }
        return -1;
    }

    public Integer getCurrentGameStage(){
        Optional <Party> recentParty = partyRepository.findFirstByOrderByCreatedAtDesc();
        System.out.println("STAGE : "+ recentParty.get().getCurrentGameStage());
        return recentParty.get().getCurrentGameStage();
    }

    public Integer getCurrentGameStageQuery() {
        return partyRepository.getCurrentGameStage();
    }

    public Integer getCurrentPartyType (){
        return partyRepository.getCurrentPartyType();
    }
}
