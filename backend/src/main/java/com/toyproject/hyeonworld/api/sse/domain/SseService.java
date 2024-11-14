package com.toyproject.hyeonworld.api.sse.domain;

import com.toyproject.hyeonworld.api.sse.constant.EmitterType;
import com.toyproject.hyeonworld.api.sse.domain.dto.SseInfo;
import com.toyproject.hyeonworld.api.sse.infrastructure.SseRepository;
import com.toyproject.hyeonworld.api.sse.interfaces.SseManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 14.
 */
@Service
@RequiredArgsConstructor
public class SseService {
    private final SseRepository sseRepository;

    public void logIn(long partyId, long userId, String userName) {
        SseManager sseManager = sseRepository.save(SseInfo.toEntity(partyId));
        sseManager.add(EmitterType.WAITING_LIST, userId);
        sseManager.registerNameOnWaitingList(userName);
    }

    public void gameOut(long partyId, long userId, String userName) {
        SseManager sseManager = sseRepository.findByPartyId(partyId);
        sseManager.remove(EmitterType.WAITING_LIST, userId);
        sseManager.registerNameOnWaitingList(userName);
    }

    public void gameIn(long partyId, long userId, String userName) {
        SseManager sseManager = sseRepository.findByPartyId(partyId);
        sseManager.add(EmitterType.WAITING_LIST, userId);
        sseManager.removeNameFromWaitingList(userName);
    }

    public void logOut(long partyId, long userId, String userName) {
        SseManager sseManager = sseRepository.findByPartyId(partyId);
        sseManager.removeNameFromWaitingList(userName);
        sseManager.remove(EmitterType.ALL, userId);
    }
}
