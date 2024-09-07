package com.toyproject.hyeonworld.api.submission.interfaces;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

import com.toyproject.hyeonworld.api.submission.domain.dto.SubmissionService;
import com.toyproject.hyeonworld.api.submission.event.SubmissionEvent;
import com.toyproject.hyeonworld.api.submission.event.SubmissionEvent.Basic;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
* @author : hyeonwoody@gmail.com
* @since : 24. 9. 7.
*/
@Component
@RequiredArgsConstructor
public class SubmissionEventListenerImpl implements SubmissionEventListener{
  private final SubmissionService submissionService;

  @Override
  @Async
  @TransactionalEventListener(phase = AFTER_COMMIT)
  public void handleSubmissionEvent(SubmissionEvent event) {
    switch (event.getClass().getSimpleName()){
      case "Basic":
        handleSubmissionBasicEvent((SubmissionEvent.Basic) event);
        break;
      default:
        throw new IllegalArgumentException("Unexpected event type: " + event.getClass());}
  }

  @Override
  public void handleSubmissionBasicEvent(Basic event) {
    submissionService.hand(event.roundId(), event.command());
  }
}
