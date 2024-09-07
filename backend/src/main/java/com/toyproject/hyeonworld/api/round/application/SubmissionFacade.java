package com.toyproject.hyeonworld.api.round.application;

import static com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionInfo.*;

import com.toyproject.hyeonworld.api.round.domain.RoundService;
import com.toyproject.hyeonworld.api.round.domain.dto.out.RoundInfo;
import com.toyproject.hyeonworld.api.submission.domain.dto.in.SubmissionCommand;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionInfo;
import com.toyproject.hyeonworld.api.round.event.SubmissionEvent;
import com.toyproject.hyeonworld.api.round.event.SubmissionEventPublisher;
import com.toyproject.hyeonworld.common.annotation.Facade;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 7.
 */
@Facade
@RequiredArgsConstructor
public class SubmissionFacade {
  private final RoundService roundService;
  private final SubmissionEventPublisher submissionEventPublisher;

  @Transactional
  public SubmissionInfo handSubmission(SubmissionCommand command) {
    RoundInfo roundInfo = roundService.retrieveCurrentRound(command.partyId());
    submissionEventPublisher.execute(new SubmissionEvent.Basic(roundInfo.getId(), command));
    return from(roundInfo.getId(), command);
  }

}
