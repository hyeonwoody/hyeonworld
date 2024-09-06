package com.toyproject.hyeonworld.api.round.infrastructure.jpa;

import com.toyproject.hyeonworld.api.round.infrastructure.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 6.
 */
public interface RoundJpaRepository extends JpaRepository<Round, Long> {

}
