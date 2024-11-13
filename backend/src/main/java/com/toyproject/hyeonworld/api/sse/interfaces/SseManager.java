package com.toyproject.hyeonworld.api.sse.interfaces;

import com.toyproject.hyeonworld.api.sse.constant.EmiitterType;
import com.toyproject.hyeonworld.api.sse.domain.dto.EmitterManager;
import com.toyproject.hyeonworld.api.sse.domain.dto.WaitingListEmitter;
import com.toyproject.hyeonworld.controller.sse.DataMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 4.
 */
@Component
@RequiredArgsConstructor
public class SseManager {
  private final WaitingListEmitter waitingListEmitter;
  private final EmitterManager emitterManager;


  public void registerWaitingList(String userName) {
    //waitingListEmitter.registerWaitingList(partyId, userName);
    emitterManager.send("registerWaitingList", DataMap.mapOf("userName", userName));
  }

  public void removeWaitingList(String userName){
    emitterManager.send("removeWaitingList", DataMap.mapOf("userName", userName));
  }

  //Nobody listens to this.
  public void add(long userId) {
    emitterManager.addEmitter(userId, "currentGame");
  }

  public void subscribeWaitingList(long userId) {
    //emitterManager.addEmitter(userId, EmiitterType.);
  }

  public void remove(long userId) {
    emitterManager.removeEmitter(userId);
  }
}
