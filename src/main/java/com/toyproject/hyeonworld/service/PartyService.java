package com.toyproject.hyeonworld.service;

import com.toyproject.hyeonworld.entity.Party;
import com.toyproject.hyeonworld.repository.PartyRepository;
import org.springframework.stereotype.Service;

@Service
public class PartyService {

    private final PartyRepository partyRepository;

    public PartyService(PartyRepository partyRepository) {
        this.partyRepository = partyRepository;
    }

    public void init(Party party) {
        partyRepository.deleteAll();
        partyRepository.save(party);
    }
}
