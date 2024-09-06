package com.toyproject.hyeonworld.api.party.application;

import static com.toyproject.hyeonworld.api.party.infrastructure.entity.Party.*;

import com.toyproject.hyeonworld.api.party.application.port.in.BeginPartyCommand;
import com.toyproject.hyeonworld.api.party.domain.PartyService;
import com.toyproject.hyeonworld.api.party.domain.dto.out.PartyInfo;
import com.toyproject.hyeonworld.api.party.event.PartyEvent;
import com.toyproject.hyeonworld.api.party.event.PartyEventPublisher;
import com.toyproject.hyeonworld.api.party.infrastructure.entity.Party;
import com.toyproject.hyeonworld.common.annotation.Facade;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
@Facade
@RequiredArgsConstructor
public class PartyFacade {
  private final PartyService partyService;
  private final PartyEventPublisher partyEventPublisher;

  @Transactional
  public PartyInfo begin(BeginPartyCommand command) {
    PartyInfo partyInfo = partyService.begin(command);
    partyEventPublisher.execute(new PartyEvent.Begin(partyInfo.getId()));
    return partyInfo;
  }

  @Transactional
  public long terminate(long partyId) {
    PartyInfo partyInfo = partyService.retrieveById(partyId);
    partyEventPublisher.execute(new PartyEvent.Terminate(partyInfo.getId()));
    return partyInfo.getId();
  }
}
