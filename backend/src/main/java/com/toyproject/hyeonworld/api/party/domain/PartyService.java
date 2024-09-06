package com.toyproject.hyeonworld.api.party.domain;

import static com.toyproject.hyeonworld.api.party.domain.dto.out.PartyInfo.*;

import com.toyproject.hyeonworld.api.party.domain.dto.out.PartyInfo;
import com.toyproject.hyeonworld.api.party.application.port.in.BeginPartyCommand;
import com.toyproject.hyeonworld.api.party.infrastructure.PartyRepository;
import com.toyproject.hyeonworld.common.exception.ServerException;
import com.toyproject.hyeonworld.common.exception.dto.ServerResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
* @author : hyeonwoody@gmail.com
* @since : 24. 9. 5.
*/
@Service
@RequiredArgsConstructor
public class PartyService {
  private final PartyRepository partyRepository;

  public PartyInfo retrieveById(long partyId) {
    return from(partyRepository.findById(partyId)
        .orElseThrow(()->new ServerException(ServerResponseCode.PARTY_NOT_FOUND)));
  }

  public PartyInfo begin(BeginPartyCommand command) {
    return from(partyRepository.save(create(command)));
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void terminate(long partyId) {
    PartyInfo partyInfo = this.retrieveById(partyId);
    partyRepository.save(partyInfo.entityToTerminate());
  }
}
