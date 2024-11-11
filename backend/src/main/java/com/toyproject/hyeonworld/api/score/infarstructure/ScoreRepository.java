package com.toyproject.hyeonworld.api.score.infarstructure;

import com.toyproject.hyeonworld.api.score.infarstructure.entity.Score;
import com.toyproject.hyeonworld.api.score.infarstructure.entity.ScoreHistory;
import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
public interface ScoreRepository {
  List<Score> saveAll (List<Score> score);



}
