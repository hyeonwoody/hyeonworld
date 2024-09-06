package com.toyproject.hyeonworld.controller.sse;

import com.toyproject.hyeonworld.service.MemberService;
import com.toyproject.hyeonworld.service.PartyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


public class SseEmitters {

    private final MemberService memberService;
    private final PartyService partyService;

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

    public SseEmitters (MemberService memberService, PartyService partyService){
        this.counter = new AtomicInteger(0);
        this.memberService = memberService;
        this.partyService = partyService;
    }

    public Integer size (){
        return this.emitterList.size();
    }

    public void remove (Long id){
        emitterList.removeIf(emitter -> emitter.getId() == id);
    }

    public boolean empty() {
        return (this.emitterList.size() == 0);
    }





    public CustomSseEmitter add(CustomSseEmitter emitter){
        this.emitterList.add(emitter);
        emitter.onCompletion(()->{
            this.emitterList.remove(emitter); //Emitter 객체 다시 생성 되기 때문에 자기 자신 지우기.
        });
        emitter.onTimeout(()->{
            emitter.complete(); //타임아웃 발생 시, 브라우저에서 재연결 요청 & 새로운 Emitter 객체 다시 생성.
        });

        return emitter;
    }

    public void send (String eventName){
        DataMap dataMap = new DataMap();
        switch (eventName){
            case "currentGameStage":
                Integer currentGameStage = partyService.getCurrentGameStage();
                System.out.println("SEND : :"+currentGameStage);
                send (eventName, dataMap.mapOf("gameStage", currentGameStage));
                break;
            case "WaitingList" :
                List<String> waitngList = memberService.getWaitingList();
                System.out.println("SEND : : :"+ waitngList.toString());
                send (eventName, dataMap.mapOf("waitingList", waitngList));
                break;

            default:
                break;
        }
    }

    public void send (String eventName, String memberName) {
        DataMap dataMap = new DataMap();
        switch (eventName){
            case "AddWaitingList":
            case "RemoveWaitingList":
                send (eventName, dataMap.mapOf("memberName", memberName));
                break;
            default:
                break;
        }
    }

    public void send (String eventName, Map<String, Integer> data ){
        ExecutorService executorService = Executors.newFixedThreadPool(emitterList.size());

        List<Callable<Void>> tasks = new ArrayList<>();

        emitterList.forEach(emitter->{
                Callable<Void> task = () -> {
                    try {
                        System.out.println("출력" + eventName);
                        System.out.println("출력 카운터" + counter.incrementAndGet());
                        emitter.send(CustomSseEmitter.event()
                                .name(eventName)
                                .data(data));
                    }catch (IOException e){
                        throw new RuntimeException(e);
                    }
                    return null;
                };
                tasks.add (task);
        });

        try {
            executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            // Handle the interruption exception
        } finally {
            executorService.shutdown();
        }

    }
}
