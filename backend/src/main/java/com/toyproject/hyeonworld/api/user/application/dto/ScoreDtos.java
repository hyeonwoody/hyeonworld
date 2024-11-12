package com.toyproject.hyeonworld.api.user.application.dto;

import com.toyproject.hyeonworld.api.score.infarstructure.entity.ScoreHistory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 10. 1.
 */
public class ScoreDtos extends ArrayList<ScoreDto> {

  public ScoreDtos(List<ScoreDto> scoreDtos) {
    super(scoreDtos != null ? scoreDtos : Collections.emptyList());
  }

  public static ScoreDtos from(List<ScoreHistory> scoreHistories) {
    return new ScoreDtos(scoreHistories.stream()
        .map(ScoreDto::from)
        .collect(Collectors.toList()));
  }

  public static HashMap<Long, Long> toSum(ScoreDtos scoreDtos) {
    HashMap<Long, Long> ret = new HashMap<>();
    for (ScoreDto info : scoreDtos){
      ret.merge(info.userId(), info.score(), Long::sum);
    }
    return ret;
  }
}