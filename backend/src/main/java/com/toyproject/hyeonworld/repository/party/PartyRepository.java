package com.toyproject.hyeonworld.repository.party;

import com.toyproject.hyeonworld.entity.Party;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface PartyRepository  {

    Optional<Party> findFirstByOrderByCreatedAtDesc();


    Optional<Party> findRecentOne();


    Integer getCurrentGame();


    Integer getCurrentGameStage();


    Integer getCurrentPartyType();
}
