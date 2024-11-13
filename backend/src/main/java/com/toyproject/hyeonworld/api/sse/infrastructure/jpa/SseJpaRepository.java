package com.toyproject.hyeonworld.api.sse.infrastructure.jpa;

import com.toyproject.hyeonworld.api.sse.infrastructure.entity.Sse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 14.
 */
public interface SseJpaRepository extends JpaRepository<Sse, Long> {

}
