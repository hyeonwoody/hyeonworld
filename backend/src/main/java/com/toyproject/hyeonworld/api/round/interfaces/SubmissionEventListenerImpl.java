package com.toyproject.hyeonworld.api.round.interfaces;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

import com.toyproject.hyeonworld.api.answerSubmission.domain.AnswerSubmissionService;
import com.toyproject.hyeonworld.api.round.event.Submission.AnswerSubmissionEvent;
import com.toyproject.hyeonworld.api.round.event.Submission.PrimarySubmissionEvent;
import com.toyproject.hyeonworld.api.round.event.Submission.SubmissionEvent;
import com.toyproject.hyeonworld.api.submission.domain.SubmissionService;

import com.toyproject.hyeonworld.api.game.strategy.GameStrategyFactory;
import com.toyproject.hyeonworld.api.round.domain.RoundService;
import com.toyproject.hyeonworld.api.round.domain.dto.out.RoundInfo;

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
public class SubmissionEventListenerImpl implements SubmissionEventListener {

    private final SubmissionService submissionService;
    private final AnswerSubmissionService answerSubmissionService;
    private final RoundService roundService;

    @Override
    @Async
    @TransactionalEventListener(phase = AFTER_COMMIT)
    public void handleSubmissionEvent(SubmissionEvent event) {
        if (event instanceof AnswerSubmissionEvent) {
            handleAnswerSubmissionEvent((AnswerSubmissionEvent) event);
        }
        if (event instanceof PrimarySubmissionEvent) {
            handlePrimarySubmissionEvent((PrimarySubmissionEvent) event);
        }
    }

    @Override
    public void handleAnswerSubmissionEvent(AnswerSubmissionEvent event) {
        RoundInfo roundInfo = roundService.retrieveCurrentRound(event.partyId());
        answerSubmissionService.submitAnswer(roundInfo.getId(),
                event.userId(), event.answer());
    }

    @Override
    public void handlePrimarySubmissionEvent(PrimarySubmissionEvent event) {
        submissionService.handFromParticipants(event.roundId(), event.command());
    }
}
