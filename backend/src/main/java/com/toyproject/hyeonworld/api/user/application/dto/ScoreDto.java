package com.toyproject.hyeonworld.api.user.application.dto;

import com.toyproject.hyeonworld.api.score.infarstructure.entity.ScoreHistory;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 10. 1.
 */

public record ScoreDto(
        long userId,
        long score
){


  public ScoreDto(long userId, long score) {
    this.userId = userId;
    this.score = score;
  }

  public static ScoreDto from(ScoreHistory scoreHistory) {
    return new ScoreDto(scoreHistory.getUserId() ,scoreHistory.getScore());
  }

  public static ScoreDto from(Long userId, Long score) {
    return new ScoreDto(userId, score);
  }
}
