package com.toyproject.hyeonworld.api.round.application;

import static com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionInfo.*;

import com.toyproject.hyeonworld.api.game.strategy.dto.StringOrLong;
import com.toyproject.hyeonworld.api.round.domain.RoundService;
import com.toyproject.hyeonworld.api.round.domain.dto.in.SubmissionCheckConfirmCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.out.RoundInfo;
import com.toyproject.hyeonworld.api.submission.domain.dto.SubmissionService;
import com.toyproject.hyeonworld.api.submission.domain.dto.in.SubmissionCheckCommand;
import com.toyproject.hyeonworld.api.submission.domain.dto.in.SubmissionCommand;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.RoundSubmissionInfo;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.RoundSubmissionInfos;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionInfo;
import com.toyproject.hyeonworld.api.round.event.SubmissionEvent;
import com.toyproject.hyeonworld.api.round.event.SubmissionEventPublisher;
import com.toyproject.hyeonworld.api.user.domain.UserService;
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
  private final SubmissionService submissionService;
  private final UserService userService;
  private final SubmissionEventPublisher submissionEventPublisher;

  @Transactional
  public SubmissionInfo submitSubmission(SubmissionCommand command) {
    RoundInfo roundInfo = roundService.retrieveCurrentRound(command.partyId());
    submissionEventPublisher.execute(new SubmissionEvent.Basic(roundInfo.getId(), command));
    return from(roundInfo.getId(), command);
  }

  @Transactional
  public RoundSubmissionInfos check(SubmissionCheckCommand command) {
    RoundSubmissionInfos roundSubmissionInfos = submissionService.check(command.roundId());

    for (RoundSubmissionInfo roundSubmissionInfo : roundSubmissionInfos){
      String userName = userService.getNameById(roundSubmissionInfo.getUserId());
      roundSubmissionInfo.complete(userName);
    }
    return roundSubmissionInfos;
  }

  @Transactional
  public RoundSubmissionInfo checkConfirm (SubmissionCheckConfirmCommand command) {
    StringOrLong<?> answer = submissionService.checkConfirm(command);
    updateRoundAnswer(command.roundId(), answer);
    return new RoundSubmissionInfo(command);
  }

  private RoundInfo updateRoundAnswer (long roundId, StringOrLong<?> answer){
    if (answer.isLong()){
      return roundService.updateAnswer(roundId, (long) answer.getValue());
    }
    if (answer.isString()){
      return roundService.updateAnswer(roundId, (String) answer.getValue());
    }
    throw new IllegalArgumentException("Unsupported answer type: " + answer.getClass().getSimpleName());
  }

}
