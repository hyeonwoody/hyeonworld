package com.toyproject.hyeonworld.api.round.domain.dto.out;

import com.toyproject.hyeonworld.api.score.infarstructure.entity.Score;
import com.toyproject.hyeonworld.api.score.infarstructure.entity.ScoreHistory;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 18.
 */
public class ScoreInfo {


  public static ScoreInfo from(List<ScoreHistory> scoreHistories) {
    return null;
  }


  public static Score from(long partyId, long userId, long score) {
    return Score.create(partyId, userId, score);
  }

  public static List<Score> from(long partyId, Map<Long, Long> userSumScore) {
    return userSumScore.entrySet().stream()
        .map(entry -> ScoreInfo.from(partyId, entry.getKey(), entry.getValue()))
        .collect(Collectors.toList());
  }
}
