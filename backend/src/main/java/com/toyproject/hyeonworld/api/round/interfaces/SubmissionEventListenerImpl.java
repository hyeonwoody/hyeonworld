package com.toyproject.hyeonworld.api.round.interfaces;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

import com.toyproject.hyeonworld.api.submission.domain.SubmissionService;

import com.toyproject.hyeonworld.api.game.strategy.GameStrategyFactory;
import com.toyproject.hyeonworld.api.round.domain.RoundService;
import com.toyproject.hyeonworld.api.round.domain.dto.out.RoundInfo;
import com.toyproject.hyeonworld.api.round.event.SubmissionEvent.Answer;

import com.toyproject.hyeonworld.api.round.event.SubmissionEvent;
import com.toyproject.hyeonworld.api.round.event.SubmissionEvent.Basic;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.AnswerSubmissionInfo;
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
  private final GameStrategyFactory gameStrategyFactory;
  private final SubmissionService submissionService;
  private final RoundService roundService;

  @Override
  @Async
  @TransactionalEventListener(phase = AFTER_COMMIT)
  public void handleSubmissionEvent(SubmissionEvent event) {
    switch (event.getClass().getSimpleName()){
      case "Basic":
        handleSubmissionBasicEvent((SubmissionEvent.Basic) event);
        break;
      case "Answer":
        handleSubmissionAnswerEvent((SubmissionEvent.Answer) event);
        break;
      default:
        throw new IllegalArgumentException("Unexpected event type: " + event.getClass());}
  }



  public void handleSubmissionAnswerEvent(Answer event) {
    RoundInfo roundInfo = roundService.retrieveCurrentRound(event.command().partyId());
    AnswerSubmissionInfo answerSubmissionInfo = submissionService.submitAnswer(roundInfo.getId(), event.command());
  }

  public void handleSubmissionBasicEvent(Basic event) {
    submissionService.hand(event.roundId(), event.command());
  }
}
