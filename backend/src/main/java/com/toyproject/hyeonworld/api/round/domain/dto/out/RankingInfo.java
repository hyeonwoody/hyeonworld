package com.toyproject.hyeonworld.api.round.domain.dto.out;


import java.util.List;
import java.util.PriorityQueue;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 10. 1.
 */
public record RankingInfo (
        PriorityQueue<UserNameScoreInfo> participants
){


  public RankingInfo(List<UserNameScoreInfo> userScoreInfos) {
    this(new PriorityQueue<UserNameScoreInfo>(
            (a, b) -> Long.compare(b.score(), a.score())
    ));
    participants.addAll(userScoreInfos);
  }

  public static RankingInfo from(List<UserNameScoreInfo> userScoreInfos) {
    return new RankingInfo(userScoreInfos);
  }
}
