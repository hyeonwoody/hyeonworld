package com.toyproject.hyeonworld.api.sse.domain.dto;

import com.toyproject.hyeonworld.api.sse.constant.EventType;
import com.toyproject.hyeonworld.api.sse.interfaces.CustomEmitter;
import com.toyproject.hyeonworld.api.sse.interfaces.CustomEmitters;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 14.
 */
public class WaitingListEmitters extends CustomEmitters {

    public WaitingListEmitters() {
        super();
    }

    public void send(EventType eventType, String name) {
        ExecutorService executorService = Executors.newFixedThreadPool(emitters.size());
        List<Callable<Void>> tasks = getThreadTasks(eventType, name);
        try {
            executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            executorService.shutdown();
        }
    }

    private List<Callable<Void>> getThreadTasks(EventType eventType, String name) {
        return emitters.stream()
                .map(emiiter -> getVoidCallable(eventType, name, emiiter))
                .toList();
    }

    private Callable<Void> getVoidCallable(EventType eventType, String name, CustomEmitter emitter) {
        return () -> {
            try {
                emitter.send(CustomEmitter.event()
                        .name(eventType.name())
                        .data(name));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        };
    }
}
