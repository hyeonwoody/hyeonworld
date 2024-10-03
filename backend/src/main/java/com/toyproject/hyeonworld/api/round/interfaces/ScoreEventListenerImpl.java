package com.toyproject.hyeonworld.api.round.interfaces;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

import com.toyproject.hyeonworld.api.round.event.ScoreEvent;
import com.toyproject.hyeonworld.api.round.event.ScoreEvent.Ranking;
import com.toyproject.hyeonworld.api.round.event.ScoreEventPublisher;
import com.toyproject.hyeonworld.api.round.event.SubmissionEvent;
import com.toyproject.hyeonworld.api.score.domain.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 10. 2.
 */
@Component
@RequiredArgsConstructor
public class ScoreEventListenerImpl implements ScoreEventListener {
  private final ScoreService scoreService;

  @Override
  @Async
  @TransactionalEventListener(phase = AFTER_COMMIT)
  public void handleScoreEvent(ScoreEvent event) {
    switch (event.getClass().getSimpleName()){
      case "Ranking":
        handleScoreRankingEvent((ScoreEvent.Ranking) event);
        break;
      default:
        throw new IllegalArgumentException("Unexpected event type: " + event.getClass());}
  }

  @Override
  public void handleScoreRankingEvent(Ranking event) {
    scoreService.save(event.partyId(), event.userScores());
  }
}
