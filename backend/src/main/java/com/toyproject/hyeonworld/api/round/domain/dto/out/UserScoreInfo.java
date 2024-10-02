package com.toyproject.hyeonworld.api.round.domain.dto.out;

import com.toyproject.hyeonworld.api.score.infarstructure.entity.Score;
import com.toyproject.hyeonworld.api.score.infarstructure.entity.ScoreHistory;
import java.util.List;
import java.util.PriorityQueue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 10. 1.
 */
@Getter
@AllArgsConstructor
public class UserScoreInfo {
  private long userId;
  private long score;

  public static UserScoreInfo from(ScoreHistory scoreHistory) {
    return new UserScoreInfo(scoreHistory.getUserId() ,scoreHistory.getScore());
  }
}
