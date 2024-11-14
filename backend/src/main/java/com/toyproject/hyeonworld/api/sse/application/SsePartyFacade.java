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
        long partyId = getPartyId(relationType);
        sseService.logIn(partyId, userId, userName);
    }

    public void gameIn(byte relationType, long userId, String userName) {
        long partyId = getPartyId(relationType);
        sseService.gameIn(partyId, userId, userName);
    }

    public void gameOut(byte relationType, long userId, String userName) {
        long partyId = getPartyId(relationType);
        sseService.gameOut(partyId, userId, userName);
    }

    public void logOut(byte relationType, long userId, String userName) {
        long partyId = getPartyId(relationType);
        sseService.logIn(partyId, userId, userName);
    }

    private long getPartyId(byte relationType) {
        return partyService.retrieveByRelationType(relationType);
    }



}
