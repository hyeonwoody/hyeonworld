package com.toyproject.hyeonworld.api.sse.infrastructure;

import com.toyproject.hyeonworld.api.sse.infrastructure.entity.Sse;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 14.
 */
public interface SseRepository {

    Sse save(Sse sse);
}
