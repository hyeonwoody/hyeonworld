package com.toyproject.hyeonworld.api.sse.infrastructure;

import com.toyproject.hyeonworld.api.sse.infrastructure.entity.Sse;
import com.toyproject.hyeonworld.api.sse.infrastructure.jpa.SseJpaRepository;
import com.toyproject.hyeonworld.api.sse.interfaces.SseManager;
import java.util.HashMap;
import java.util.Map;
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
    private final Map<Long, SseManager> sseCollection = new HashMap<>();
    private final SseJpaRepository sseJpaRepository;

    @Override
    public Sse save(Sse sse) {
        sseCollection.put(sse.getPartyId(), SseManager.from());
        return sseJpaRepository.save(sse);
    }
}
