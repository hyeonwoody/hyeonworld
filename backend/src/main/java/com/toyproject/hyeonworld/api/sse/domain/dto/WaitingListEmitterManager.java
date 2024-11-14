package com.toyproject.hyeonworld.api.sse.domain.dto;

import com.toyproject.hyeonworld.api.sse.constant.EventType;
import com.toyproject.hyeonworld.api.sse.interfaces.EmitterManager;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 4.
 */
public class WaitingListEmitterManager implements EmitterManager {

    private WaitingListEmitters waitingListEmitters;
    private final List<String> waitingList;

    public WaitingListEmitterManager() {
        this.waitingListEmitters = new WaitingListEmitters();
        this.waitingList = new ArrayList<>();
    }

//  public void addEmitter(long userId) {
//    CustomEmitter emitter = new CustomEmitter(userId);
//    emitter.onCompletion(() -> this.emitterList.remove(emitter));
//    emitter.onTimeout(emitter::complete);
//    this.emitterList.add(emitter);
//  }

    @Override
    public void add(long userId) {
        waitingListEmitters.add(userId);
    }

//  public void removeEmitter(long userId) {
//    List<CustomEmitter> emittersToRemove = this.emitterList.stream()
//            .filter(emitter -> emitter.getId() == userId)
//            .toList();
//    this.emitterList.removeAll(emittersToRemove);
//    emittersToRemove.forEach(CustomEmitter::complete);
//  }

    @Override
    public void remove(long userId) {
        waitingListEmitters.remove(userId);
    }

    public void registerNameOnWaitingList(String name) {
        waitingList.add(name);
        waitingListEmitters.send(EventType.ADD_NAME_TO_WAITING_LIST, name);
    }

    public void removeNameOnWaitingList(String name) {
        waitingList.remove(name);
        waitingListEmitters.send(EventType.REMOVE_NAME_FROM_WAITING_LIST, name);
    }

}
