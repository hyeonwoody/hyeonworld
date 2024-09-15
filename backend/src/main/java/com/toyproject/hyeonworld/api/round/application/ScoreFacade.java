package com.toyproject.hyeonworld.api.round.application;

import com.toyproject.hyeonworld.api.round.domain.RoundService;
import com.toyproject.hyeonworld.api.round.domain.dto.out.ResultInfo;
import com.toyproject.hyeonworld.api.score.domain.ScoreService;
import com.toyproject.hyeonworld.api.submission.domain.dto.SubmissionService;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.AnswerSubmissionInfo;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.AnswerSubmissionInfos;
import com.toyproject.hyeonworld.api.user.domain.UserService;
import com.toyproject.hyeonworld.common.annotation.Facade;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
@Facade
@RequiredArgsConstructor
public class ScoreFacade {
  private final SubmissionService submissionService;
  private final UserService userService;
  private final RoundService roundService;
  private final ScoreService scoreService;

  public ResultInfo result(long roundId) {
    AnswerSubmissionInfos answerSubmissionInfos = submissionService.retrieveAnswerSubmissions(roundId);
    String answer = roundService.retrieveAnswer(roundId);
    ResultInfo resultInfo = new ResultInfo(answer);

    List<Long> winnerIds = answerSubmissionInfos.stream()
        .filter(answerSubmissionInfo -> answer.equals(answerSubmissionInfo.getAnswer()))
        .map(AnswerSubmissionInfo::getUserId)
        .toList();
    Map<Long, String> userNames = userService.getNamesByIds(winnerIds);
    userNames.values().forEach(resultInfo::addWinner);
    return resultInfo;
  }
}
