package com.toyproject.hyeonworld.api.party.interfaces;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

import com.toyproject.hyeonworld.api.party.domain.PartyDashboardService;
import com.toyproject.hyeonworld.api.party.event.PartyEvent;
import com.toyproject.hyeonworld.api.party.event.PartyEvent.Begin;
import com.toyproject.hyeonworld.api.session.event.SessionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
@Component
@RequiredArgsConstructor
public class PartyEventListenerImpl implements PartyEventListener{
  private final PartyDashboardService partyDashboardService;

  @Override
  @Async
  @TransactionalEventListener(phase = AFTER_COMMIT)
  public void handlePartyEvent(PartyEvent event) {
    switch (event.getClass().getSimpleName()){
      case "Begin":
        handlePartyBeginEvent((PartyEvent.Begin) event);
        break;
      default:
        throw new IllegalArgumentException("Unexpected event type: " + event.getClass());}
  }

  public void handlePartyBeginEvent(PartyEvent.Begin event) {
    partyDashboardService.createPartyDashboard(event.partyId());
  }
}
