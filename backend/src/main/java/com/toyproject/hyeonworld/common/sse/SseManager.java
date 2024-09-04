package com.toyproject.hyeonworld.common.sse;

import com.toyproject.hyeonworld.controller.sse.DataMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 4.
 */
@Component
@RequiredArgsConstructor
public class SseManager {
  private final EmitterManager emitterManager;


  public void registerWaitingList(String userName) {
    emitterManager.send("registerWaitingList", DataMap.mapOf("userName", userName));
  }

  public void removeWaitingList(String userName){
    emitterManager.send("removeWaitingList", DataMap.mapOf("userName", userName));
  }

  //Nobody listens to this.
  public void add(long userId) {
    emitterManager.addEmitter(userId, "currentGame");


  }

  public void remove(long userId) {
    emitterManager.removeEmitter(userId);
  }
}
