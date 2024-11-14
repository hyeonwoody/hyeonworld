package com.toyproject.hyeonworld.api.session.interfaces;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

import com.toyproject.hyeonworld.api.session.event.GameInSessionEvent;
import com.toyproject.hyeonworld.api.session.event.GameOutSessionEvent;
import com.toyproject.hyeonworld.api.session.event.LogInSessionEvent;
import com.toyproject.hyeonworld.api.session.event.LogOutSessionEvent;
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

  void handleLoginSessionEvent(SessionEvent sessionEvent);
  void handleGameInSessionEvent(SessionEvent sessionEvent);
  void handleGameOutSessionEvent(SessionEvent sessionEvent);
  void handleLogoutSessionEvent(SessionEvent sessionEvent);
}
