package com.toyproject.hyeonworld.api.score.infarstructure.jpa;

import com.toyproject.hyeonworld.api.score.infarstructure.entity.ScoreHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
public interface ScoreHistoryJpaRepository extends JpaRepository<ScoreHistory, Long> {

}
