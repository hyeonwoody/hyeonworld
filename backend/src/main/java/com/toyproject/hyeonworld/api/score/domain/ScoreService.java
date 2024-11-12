package com.toyproject.hyeonworld.api.score.domain;

import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundPlayCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundResultConfirmCommand;
import com.toyproject.hyeonworld.api.user.application.dto.ScoreDtos;
import com.toyproject.hyeonworld.api.score.domain.dto.out.ScoreInfo;
import com.toyproject.hyeonworld.api.score.domain.dto.out.ScoreHistoryInfo;
import com.toyproject.hyeonworld.api.score.infarstructure.ScoreHistoryRepository;
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
  private final ScoreHistoryRepository scoreHistoryRepository;

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void submitAnswer(RoundPlayCommand command, long roundId, String answer) {
    scoreHistoryRepository.save(ScoreHistoryInfo.createEntity(command, roundId, answer));
  }

  public ScoreInfo updateScore(long partyId, RoundResultConfirmCommand command) {
      return ScoreInfo.from(scoreHistoryRepository.saveAll(ScoreHistoryInfo.createEntities(partyId, command)));
  }

  public ScoreDtos retrieveScores(long partyId) {
    return ScoreDtos.from(scoreHistoryRepository.findByPartyId(partyId));
  }

  public HashMap<Long, Long> retrieveSumScores(long partyId) {
    return ScoreDtos.toSum(retrieveScores(partyId));
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void save(long partyId, Map<Long, Long> userSumScore) {
      scoreRepository.saveAll(ScoreInfo.from(partyId, userSumScore));
  }
}
