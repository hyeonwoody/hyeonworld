package com.toyproject.hyeonworld.controller.sse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@Slf4j //로깅이 유용
public class SseEmitters {
    private final List<SseEmitter> emitterList = new CopyOnWriteArrayList<>();

    public SseEmitter add(SseEmitter emitter){
        this.emitterList.add(emitter);

        emitter.onCompletion(()->{
            this.emitterList.remove(emitter);
        });
        emitter.onTimeout(()->{
            emitter.complete();
        });

        return emitter;
    }
}
