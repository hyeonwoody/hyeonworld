package com.toyproject.hyeonworld.api.score.infarstructure;

import com.toyproject.hyeonworld.api.score.infarstructure.entity.ScoreHistory;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
public interface ScoreRepository {

  ScoreHistory save(ScoreHistory scoreHistory);
}
