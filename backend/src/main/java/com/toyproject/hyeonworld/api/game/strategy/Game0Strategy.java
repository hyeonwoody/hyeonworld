package com.toyproject.hyeonworld.api.game.strategy;

import com.toyproject.hyeonworld.api.game.strategy.dto.StringOrLong;
import com.toyproject.hyeonworld.api.round.domain.RoundService;
import com.toyproject.hyeonworld.api.round.domain.dto.in.SubmissionCheckConfirmCommand;
import com.toyproject.hyeonworld.api.submission.domain.dto.SubmissionService;
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
public class Game0Strategy implements GameStrategy{
  private final RoundService roundService;
  private final SubmissionService submissionService;
  private final UserService userService;

  @Override
  public long getGameId() {
    return 0L;
  }

  @Override
  public StringOrLong<Long> checkConfirm(SubmissionCheckConfirmCommand command) {
    SubmissionInfo submissionInfo = submissionService.retrieveByUserId(command.userId());
    return StringOrLong.ofNumber(submissionInfo.getId());
  }

  @Override
  public String show(long roundId) {
    String submissionId = roundService.retrieveAnswer(roundId);
    RoundSubmissionInfo roundSubmissionInfo = submissionService.retrieveById(Long.parseLong(submissionId));
    String userName = userService.getNameById(roundSubmissionInfo.getUserId());
    roundSubmissionInfo.complete(userName);
    return formatSubmissionText(roundSubmissionInfo);
  }

  private String formatSubmissionText(RoundSubmissionInfo info) {
    StringBuilder formattedText = new StringBuilder();
    formattedText.append(info.getName()).append("님의 진실 혹은 거짓 :\n");

    String[] statements = info.getText().split(",");
    for (int i = 0; i < statements.length; i++) {
      formattedText.append(i + 1)
          .append(". ")
          .append(statements[i].trim())
          .append("\n");
    }
    return formattedText.toString();
  }
}
