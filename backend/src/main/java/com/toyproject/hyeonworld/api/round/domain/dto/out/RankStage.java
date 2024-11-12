package com.toyproject.hyeonworld.api.round.domain.dto.out;


import com.toyproject.hyeonworld.api.user.application.dto.NameScoreDto;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 10. 1.
 */
public record RankStage(
        PriorityQueue<NameScoreDto> participants
){


  public RankStage(List<NameScoreDto> userScoreInfos) {
    this(new PriorityQueue<NameScoreDto>(
            (a, b) -> Long.compare(b.score(), a.score())
    ));
    participants.addAll(userScoreInfos);
  }

  public static RankStage from(List<NameScoreDto> userScoreInfos) {
    return new RankStage(userScoreInfos);
  }
}
