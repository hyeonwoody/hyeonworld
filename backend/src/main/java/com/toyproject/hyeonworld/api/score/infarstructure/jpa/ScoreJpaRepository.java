package com.toyproject.hyeonworld.api.score.infarstructure.jpa;

import com.toyproject.hyeonworld.api.score.infarstructure.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
public interface ScoreJpaRepository extends JpaRepository<Score, Long> {

}
