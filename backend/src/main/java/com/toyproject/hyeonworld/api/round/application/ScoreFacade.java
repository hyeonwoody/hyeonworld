package com.toyproject.hyeonworld.api.round.application;

import com.toyproject.hyeonworld.api.round.domain.RoundService;
import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundRankingCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundResultConfirmCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.out.RankingInfo;
import com.toyproject.hyeonworld.api.round.domain.dto.out.ResultInfo;
import com.toyproject.hyeonworld.api.score.domain.dto.out.ScoreInfo;
import com.toyproject.hyeonworld.api.round.event.ScoreEvent.Ranking;
import com.toyproject.hyeonworld.api.round.event.ScoreEventPublisher;
import com.toyproject.hyeonworld.api.score.domain.ScoreService;
import com.toyproject.hyeonworld.api.submission.domain.SubmissionService;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.AnswerSubmissionInfo;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.AnswerSubmissionInfos;
import com.toyproject.hyeonworld.api.user.domain.UserService;
import com.toyproject.hyeonworld.common.annotation.Facade;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

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

  private final ScoreEventPublisher scoreEventPublisher;

  public ResultInfo result(long roundId) {
    AnswerSubmissionInfos answerSubmissionInfos = submissionService.retrieveAnswerSubmissions(roundId);
    String answer = roundService.retrieveAnswer(roundId);
    ResultInfo resultInfo = new ResultInfo(answer);
    Set<Long> winnerIds = answerSubmissionInfos.stream()
        .filter(answerSubmissionInfo -> answer.equals(answerSubmissionInfo.getAnswer()))
        .map(AnswerSubmissionInfo::getUserId)
        .collect(Collectors.toSet());

    Map<Long, String> userNames = userService.getNamesByIds(winnerIds);
    for (Long winnerId : winnerIds) {
      String winnerName = userNames.get(winnerId);
      if (winnerName != null) {
        resultInfo.addWinner(winnerId, winnerName);
      }
    }
    return resultInfo;
  }

  public ScoreInfo resultScore(RoundResultConfirmCommand command) {
    long partyId = roundService.retrievePartyId(command.roundId());
    command = new RoundResultConfirmCommand(partyId, command.roundId(), command.winners());

    ScoreInfo scoreInfo = scoreService.updateScore(command);
    return scoreInfo;
  }

  @Transactional
  public RankingInfo ranking(RoundRankingCommand command) {
    Map<Long, Long> userScores = scoreService.retrieveSumScores(command.partyId());
    scoreEventPublisher.execute(new Ranking(command.partyId(), userScores));
    RankingInfo rankingInfo = new RankingInfo();

    for (Map.Entry<Long, Long> entry : userScores.entrySet()){
      String name = userService.getNameById(entry.getKey());
      rankingInfo.addParticipant(name, entry.getValue());
    }
    return rankingInfo;
  }
}
