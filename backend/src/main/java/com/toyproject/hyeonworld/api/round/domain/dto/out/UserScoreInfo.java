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

public record UserScoreInfo (
        long userId,
        long score
){


  public UserScoreInfo(long userId, long score) {
    this.userId = userId;
    this.score = score;
  }

  public static UserScoreInfo from(ScoreHistory scoreHistory) {
    return new UserScoreInfo(scoreHistory.getUserId() ,scoreHistory.getScore());
  }

  public static UserScoreInfo from(Long userId, Long score) {
    return new UserScoreInfo(userId, score);
  }
}
