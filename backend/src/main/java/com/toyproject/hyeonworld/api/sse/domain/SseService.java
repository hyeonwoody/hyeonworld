package com.toyproject.hyeonworld.api.sse.domain;

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
    private final SseManager sseManager;

    public void logIn(long partyId, long userId, String userName) {
        sseRepository.save(SseInfo.toEntity(partyId));
        sseManager.registerWaitingList(userName);
        sseManager.add(userId);
    }

    public void gameOut(long userId, String userName) {
        sseManager.remove(userId);
        sseManager.removeWaitingList(userName);
    }

    public void gameIn(long userId, String userName) {
        sseManager.removeWaitingList(userName);
        sseManager.subscribeWaitingList(userId);
    }

    public void logOut(long userId, String userName) {
        sseManager.removeWaitingList(userName);
        sseManager.remove(userId);
    }
}
