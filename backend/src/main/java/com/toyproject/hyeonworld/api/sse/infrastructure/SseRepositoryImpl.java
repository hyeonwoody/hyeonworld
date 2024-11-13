package com.toyproject.hyeonworld.api.sse.infrastructure;

import com.toyproject.hyeonworld.api.sse.infrastructure.entity.Sse;
import com.toyproject.hyeonworld.api.sse.infrastructure.jpa.SseJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 14.
 */
@Repository
@Primary
@RequiredArgsConstructor
public class SseRepositoryImpl implements SseRepository {
    private final SseJpaRepository sseJpaRepository;

    @Override
    public Sse save(Sse sse) {
        return sseJpaRepository.save(sse);
    }
}
