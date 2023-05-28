package com.toyproject.hyeonworld.controller.sse;

import com.toyproject.hyeonworld.service.MemberService;
import com.toyproject.hyeonworld.service.PartyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j //로깅이 유용
public class SseEmitters {

    public enum DataType {
        CURRENT_GAME_STAGE(0),
        WAITING_LIST(1);

        private final int value;

        DataType (int value){
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    private List<CustomSseEmitter> emitterList = new CopyOnWriteArrayList<>();

    static AtomicInteger counter;

    public SseEmitters (){
        this.counter = new AtomicInteger(0);
    }

    public CustomSseEmitter add(CustomSseEmitter emitter){
        this.emitterList.add(emitter);

        emitter.onCompletion(()->{
            System.out.println("삭제된다");
            this.emitterList.remove(emitter); //Emitter 객체 다시 생성 되기 때문에 자기 자신 지우기.
        });
        emitter.onTimeout(()->{
            emitter.complete(); //타임아웃 발생 시, 브라우저에서 재연결 요청 & 새로운 Emitter 객체 다시 생성.
        });

        return emitter;
    }

    public void send (String eventName){}

    public void send (String eventName, Map<String, Integer> data ){
        ExecutorService executorService = Executors.newFixedThreadPool(emitterList.size());

        emitterList.forEach( emitter->{
            executorService.submit(()->{
                try {
                    System.out.println("출력"+eventName);
                    emitter.send(CustomSseEmitter.event()
                            .name(eventName)
                            .data(data)
                    );
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        });

        executorService.shutdown();
    }
}
