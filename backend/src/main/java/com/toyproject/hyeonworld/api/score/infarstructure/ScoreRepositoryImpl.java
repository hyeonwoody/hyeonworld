package com.toyproject.hyeonworld.api.score.infarstructure;

import com.toyproject.hyeonworld.api.score.infarstructure.entity.Score;
import com.toyproject.hyeonworld.api.score.infarstructure.jpa.ScoreJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
@Repository
@Primary
@RequiredArgsConstructor
public class ScoreRepositoryImpl implements ScoreRepository{
  private final ScoreJpaRepository scoreJpaRepository;


  @Override
  public List<Score> saveAll(List<Score> score){
    return scoreJpaRepository.saveAll(score);
  }


}
