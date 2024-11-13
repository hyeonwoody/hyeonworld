package com.toyproject.hyeonworld.api.session.interfaces;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

import com.toyproject.hyeonworld.api.sse.application.SsePartyFacade;
import com.toyproject.hyeonworld.api.sse.domain.SseService;
import com.toyproject.hyeonworld.api.sse.interfaces.SseManager;
import com.toyproject.hyeonworld.api.session.event.SessionEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 4.
 */
@Component
@RequiredArgsConstructor
public class SessionEventListenerImpl implements SessionEventListener {
  private final SsePartyFacade ssePartyFacade;
  private final SseService sseService;

  @Override
  @Async
  @TransactionalEventListener(phase = AFTER_COMMIT)
  public void handleSessionEvent(SessionEvent event) {
    switch (event.getClass().getSimpleName()){
      case "Login":
        handleLoginSessionEvent((SessionEvent.Login) event);
        break;
      case "GameIn":
        handleGameInSessionEvent((SessionEvent.GameIn) event);
        break;
      case "GameOut":
        handleGameOutSessionEvent((SessionEvent.GameOut) event);
        break;
      case "Logout":
        handleLogoutSessionEvent((SessionEvent.Logout) event);
        break;
      default:
        throw new IllegalArgumentException("Unexpected event type: " + event.getClass());}
  }

  @Override
  public void handleLoginSessionEvent(SessionEvent.Login event) {
    ssePartyFacade.logIn(event.relationType(), event.userId(), event.userName());
  }

  @Override
  public void handleGameOutSessionEvent(SessionEvent.GameOut event) {
    sseService.gameOut(event.userId(), event.userName());
  }

  @Override
  public void handleGameInSessionEvent(SessionEvent.GameIn event) {
    sseService.gameIn(event.userId(), event.userName());
  }

  @Override
  public void handleLogoutSessionEvent(SessionEvent.Logout event) {
    sseService.logOut(event.userId(), event.userName());
  }

}
