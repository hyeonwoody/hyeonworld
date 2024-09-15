package com.toyproject.hyeonworld.api.score.infarstructure;

import com.toyproject.hyeonworld.api.score.infarstructure.entity.ScoreHistory;
import com.toyproject.hyeonworld.api.score.infarstructure.jpa.ScoreHistoryJpaRepository;
import com.toyproject.hyeonworld.api.score.infarstructure.jpa.ScoreJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
@Repository
@RequiredArgsConstructor
public class ScoreRepositoryImpl implements ScoreRepository{
  private final ScoreHistoryJpaRepository scoreHistoryJpaRepository;

  @Override
  public ScoreHistory save(ScoreHistory scoreHistory){
    return scoreHistoryJpaRepository.save(scoreHistory);
  }
}
