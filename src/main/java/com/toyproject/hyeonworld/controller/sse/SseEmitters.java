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

@Component
@Slf4j //로깅이 유용
public class SseEmitters {
    private final List<CustomSseEmitter> emitterList = new CopyOnWriteArrayList<>();
    private final PartyService partyService;
    private final MemberService memberService;


    public SseEmitters (PartyService partyService, MemberService memberService){
        this.partyService = partyService;
        this.memberService = memberService;
    }

    static int currentStageCnt = 0;
    static int waitingListCnt = 0;
    static int prevWaitingListSize = 0;
    public CustomSseEmitter add(CustomSseEmitter emitter){
        this.emitterList.add(emitter);

        emitter.onCompletion(()->{
            System.out.println("삭제된다");
            this.emitterList.remove(emitter);
        });
        emitter.onTimeout(()->{
            System.out.println("타임아웃");
            emitter.complete();
        });

        return emitter;
    }

    public void send (String eventName){
        DataMap dataMap = new DataMap();
        System.out.println("The SIZE : "+ this.emitterList.size());
        switch (eventName){
            case "currentGameStage" :
                System.out.println("qk");
                Integer value = partyService.getCurrentGameStage();
                send (eventName, dataMap.mapOf("stage", value));
                break;
            case "WaitingList" :
                //Integer partyType = partyService.getCurrentPartyType();
                List<String> waitingList = memberService.getWaitingList();
                send (eventName, dataMap.mapOf("list", waitingList));
                break;
            default :
                break;
        }
    }

    public void send (String eventName, Map<String, Integer> data ){
        ExecutorService executorService = Executors.newFixedThreadPool(emitterList.size());

        emitterList.forEach( emitter->{
            executorService.submit(()->{
                try {
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
