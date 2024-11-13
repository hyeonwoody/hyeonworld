package com.toyproject.hyeonworld.api.sse.application;

import com.toyproject.hyeonworld.api.party.domain.PartyService;
import com.toyproject.hyeonworld.api.sse.domain.SseService;
import com.toyproject.hyeonworld.common.annotation.Facade;
import lombok.RequiredArgsConstructor;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 14.
 */
@Facade
@RequiredArgsConstructor
public class SsePartyFacade {
    private final SseService sseService;
    private final PartyService partyService;

    public void logIn(byte relationType, long userId, String userName) {
        long partyId = partyService.retrieveByRelationType(relationType);
        sseService.logIn(partyId, userId, userName);
    }
}
