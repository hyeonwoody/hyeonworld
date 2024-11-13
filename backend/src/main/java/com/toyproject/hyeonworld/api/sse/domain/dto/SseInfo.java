package com.toyproject.hyeonworld.api.sse.domain.dto;

import com.toyproject.hyeonworld.api.score.infarstructure.entity.Score;
import com.toyproject.hyeonworld.api.sse.infrastructure.entity.Sse;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 14.
 */
public class SseInfo {
    private long partyId;

    public static Sse toEntity(long partyId) {
        return Sse.defaultBuilder()
                .partyId(partyId)
                .build();
    }
}
