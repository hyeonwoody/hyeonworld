package com.toyproject.hyeonworld.api.sse.domain.dto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 4.
 */
@Component
public class EmitterManager {
  private final List<CustomSseEmitter> emitterList = new CopyOnWriteArrayList<>();

  public void send(String eventName, Map<String, Integer> data) {
    ExecutorService executorService = Executors.newFixedThreadPool(emitterList.size());
    List<Callable<Void>> tasks = new ArrayList<>();

    emitterList.forEach(emitter -> {
      Callable<Void> task = () -> {
        try {
          if (emitter.getEventName().equals(eventName)){
            emitter.send(CustomSseEmitter.event()
                .name(eventName)
                .data(data));
          }
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        return null;
      };
      tasks.add(task);
    });

    try {
      executorService.invokeAll(tasks);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    } finally {
      executorService.shutdown();
    }
  }

  public void addEmitter(long userId, String eventType) {
    CustomSseEmitter emitter = new CustomSseEmitter(userId, eventType);
    emitter.onCompletion(() -> this.emitterList.remove(emitter));
    emitter.onTimeout(emitter::complete);
    this.emitterList.add(emitter);
  }

  public void removeEmitter(long userId) {
    List<CustomSseEmitter> emittersToRemove = this.emitterList.stream()
        .filter(emitter -> emitter.getId() == userId)
        .toList();
    this.emitterList.removeAll(emittersToRemove);
    emittersToRemove.forEach(CustomSseEmitter::complete);
  }
}
