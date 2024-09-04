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
public interface LoginEventListener extends EventListener {

  void registerWaitingList(SessionEvent.Login event);
  void removeWaitingList(SessionEvent.GameIn event);
  void registerWaitingList(SessionEvent.GameOut event);
  void registerSse(SessionEvent.Login event);
  void removeSse(SessionEvent.Logout event);

}
