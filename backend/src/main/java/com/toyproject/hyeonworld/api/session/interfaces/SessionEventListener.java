package com.toyproject.hyeonworld.api.session.interfaces;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

import com.toyproject.hyeonworld.api.session.event.SessionEvent;
import com.toyproject.hyeonworld.common.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 4.
 */
public interface SessionEventListener extends EventListener {

  void handleSessionEvent(SessionEvent event);

  void handleLoginSessionEvent(SessionEvent.Login event);
  void handleGameInSessionEvent(SessionEvent.GameIn event);
  void handleGameOutSessionEvent(SessionEvent.GameOut event);
  void handleLogoutSessionEvent(SessionEvent.Logout event);
}
