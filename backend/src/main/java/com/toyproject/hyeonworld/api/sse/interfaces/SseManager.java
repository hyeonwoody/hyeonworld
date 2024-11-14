package com.toyproject.hyeonworld.api.sse.interfaces;

import com.toyproject.hyeonworld.api.sse.constant.EmitterType;
import com.toyproject.hyeonworld.api.sse.domain.dto.WaitingListEmitterManager;
import java.util.EnumMap;
import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 4.
 */
public class SseManager {
    private EnumMap<EmitterType, EmitterManager> emitterStrategy;

    private SseManager() {
        this.emitterStrategy = new EnumMap<>(EmitterType.class);
    }

    public static SseManager from() {
        return new SseManager();
    }

    public void registerNameOnWaitingList(String userName) {
        WaitingListEmitterManager emitterManager = (WaitingListEmitterManager) getEmitterManager(EmitterType.WAITING_LIST);
        emitterManager.registerNameOnWaitingList(userName);
    }

    public void removeNameFromWaitingList(String userName) {
        WaitingListEmitterManager emitterManager = (WaitingListEmitterManager) getEmitterManager(EmitterType.WAITING_LIST);
        emitterManager.removeNameOnWaitingList(userName);
    }

    //Nobody listens to this.
    public void add(EmitterType emitterType, long userId) {
        EmitterManager emitterManager = getEmitterManager(emitterType);
        emitterManager.add(userId);
    }

    public void remove(EmitterType emitterType, long userId) {
        List<EmitterManager> emitterManagers = getEmitterManagers(emitterType);
        for (EmitterManager emitterManager : emitterManagers) {
            emitterManager.remove(userId);
        }
    }

    private EmitterManager getEmitterManager(EmitterType emitterType) {
        return emitterStrategy.get(emitterType);
    }

    private List<EmitterManager> getEmitterManagers(EmitterType emitterType) {
        if (emitterType == EmitterType.ALL) {
            return emitterStrategy.values().stream().toList();
        }
        return List.of(emitterStrategy.get(emitterType));
    }
}
