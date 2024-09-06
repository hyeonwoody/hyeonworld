package com.toyproject.hyeonworld.api.party.interfaces;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

import com.toyproject.hyeonworld.api.party.domain.PartyService;
import com.toyproject.hyeonworld.api.party.event.PartyEvent;
import com.toyproject.hyeonworld.api.party.event.PartyEvent.Begin;
import com.toyproject.hyeonworld.api.party.event.PartyEvent.Terminate;
import com.toyproject.hyeonworld.api.partyDashboard.domain.PartyDashboardService;
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
  private final PartyService partyService;
  private final PartyDashboardService partyDashboardService;

  @Override
  @Async
  @TransactionalEventListener(phase = AFTER_COMMIT)
  public void handlePartyEvent(PartyEvent event) {
    switch (event.getClass().getSimpleName()){
      case "Begin":
        handlePartyBeginEvent((PartyEvent.Begin) event);
        break;
      case "Terminate":
        handlePartyTerminateEvent((PartyEvent.Terminate) event);
        break;
      default:
        throw new IllegalArgumentException("Unexpected event type: " + event.getClass());}
  }

  private void handlePartyTerminateEvent(Terminate event) {
    partyService.terminate(event.partyId());
  }

  public void handlePartyBeginEvent(Begin event) {
    partyDashboardService.createPartyDashboard(event.partyId());
  }
}
