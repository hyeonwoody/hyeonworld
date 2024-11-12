package com.toyproject.hyeonworld.api.game.strategy;

import com.toyproject.hyeonworld.api.answerSubmission.domain.AnswerSubmissionService;
import com.toyproject.hyeonworld.api.round.domain.RoundService;
import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundPlayCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.in.SubmissionCheckConfirmCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.out.RoundInfo;
import com.toyproject.hyeonworld.api.round.event.Submission.AnswerSubmissionEvent;
import com.toyproject.hyeonworld.api.round.event.Submission.SubmissionEventPublisher;
import com.toyproject.hyeonworld.api.submission.domain.SubmissionService;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.RoundSubmissionInfo;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionInfo;
import com.toyproject.hyeonworld.api.user.domain.UserService;
import com.toyproject.hyeonworld.common.annotation.Strategy;
import lombok.RequiredArgsConstructor;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 11.
 */
@Strategy(0)
@RequiredArgsConstructor
public class TruthOrFalseStrategy implements GameStrategy<Long> {
  private final RoundService roundService;
  private final SubmissionService submissionService;
  private final AnswerSubmissionService answerSubmissionService;
  private final UserService userService;


  @Override
  public long getGameId() {
    return 0L;
  }

  @Override
  public Long checkConfirm(SubmissionCheckConfirmCommand command) {
    SubmissionInfo submissionInfo = submissionService.checkConfirmSubmission(command.userId());
    updateRoundAnswer(submissionInfo.getRoundId(), submissionInfo.getNumber());
    return submissionInfo.getNumber();
  }

  @Override
  public RoundInfo updateRoundAnswer(long roundId, Long answer){
    return roundService.updateAnswer(roundId, answer);
  }

  @Override
  public void tutorialStage() {

  }

  private String formatSubmissionText(RoundSubmissionInfo info) {
    StringBuilder formattedText = new StringBuilder();
    formattedText.append(info.getName()).append("님의 진실 혹은 거짓 :\n");

    String[] statements = info.getText().split(",");
    for (int i = 0; i < statements.length; i++) {
      formattedText.append(i + 1)
          .append(". ")
          .append(statements[i].trim())
          .append(System.lineSeparator());
    }
    return formattedText.toString();
  }

  @Override
  public String showStage(long roundId) {
    String submissionId = roundService.retrieveAnswer(roundId);
    RoundSubmissionInfo roundSubmissionInfo = submissionService.showById(Long.parseLong(submissionId));
    String userName = userService.getNameById(roundSubmissionInfo.getUserId());
    roundSubmissionInfo.complete(userName);
    return formatSubmissionText(roundSubmissionInfo);
  }

  @Override
  public Long playStage(RoundPlayCommand command) {
    RoundInfo roundInfo = roundService.retrieveCurrentRound(command.partyId());
    answerSubmissionService.submitAnswer(roundInfo.getId(),
            command.userId(), command.answer());
    return Long.parseLong(command.answer());
  }
}
