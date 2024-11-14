package com.toyproject.hyeonworld.api.sse.infrastructure;

import com.toyproject.hyeonworld.api.sse.infrastructure.entity.Sse;
import com.toyproject.hyeonworld.api.sse.interfaces.SseManager;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 14.
 */
public interface SseRepository {

    SseManager save(Sse sse);

    SseManager findByPartyId(long partyId);
}
