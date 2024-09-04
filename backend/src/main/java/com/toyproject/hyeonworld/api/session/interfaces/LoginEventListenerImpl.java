package com.toyproject.hyeonworld.api.session.interfaces;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

import com.toyproject.hyeonworld.common.sse.SseManager;
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
public class LoginEventListenerImpl implements LoginEventListener {
  private final SseManager sseManager;

  @Override
  @Async
  @TransactionalEventListener(phase = AFTER_COMMIT)
  public void registerWaitingList(SessionEvent.Login event) {
    sseManager.registerWaitingList(event.getUserName());
  }

  @Override
  @Async
  @TransactionalEventListener(phase = AFTER_COMMIT)
  public void registerSse(SessionEvent.Login event){
    sseManager.add(event.getUserId());
  }

  @Override
  @Async
  @TransactionalEventListener(phase = AFTER_COMMIT)
  public void removeSse(SessionEvent.Logout event){
    sseManager.remove(event.getUserId());
  }
}
