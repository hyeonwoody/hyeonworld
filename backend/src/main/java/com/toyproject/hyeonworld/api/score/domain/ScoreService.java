package com.toyproject.hyeonworld.api.score.domain;

import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundPlayCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundResultConfirmCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.out.UserScoreInfos;
import com.toyproject.hyeonworld.api.score.domain.dto.out.ScoreInfo;
import com.toyproject.hyeonworld.api.score.domain.dto.out.ScoreHistoryInfo;
import com.toyproject.hyeonworld.api.score.infarstructure.ScoreRepository;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
@Service
@RequiredArgsConstructor
public class ScoreService {
  private final ScoreRepository scoreRepository;

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void submitAnswer(RoundPlayCommand command, long roundId, String answer) {
    scoreRepository.save(ScoreHistoryInfo.createEntity(command, roundId, answer));
  }

  public ScoreInfo updateScore(RoundResultConfirmCommand command) {
      return ScoreInfo.from(scoreRepository.saveScoreHistoryAll(ScoreHistoryInfo.createEntities(command)));
  }

  public UserScoreInfos retrieveScores(long partyId) {
    return UserScoreInfos.from(scoreRepository.findByPartyId(partyId));
  }

  public HashMap<Long, Long> retrieveSumScores(long partyId) {
    return UserScoreInfos.toSum(retrieveScores(partyId));
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void save(long partyId, Map<Long, Long> userSumScore) {
      scoreRepository.saveScoreAll(ScoreInfo.from(partyId, userSumScore));
  }
}
