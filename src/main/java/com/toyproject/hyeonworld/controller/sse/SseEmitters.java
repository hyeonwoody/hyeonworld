package com.toyproject.hyeonworld.controller.sse;

import com.toyproject.hyeonworld.service.MemberService;
import com.toyproject.hyeonworld.service.PartyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@Slf4j //로깅이 유용
public class SseEmitters {
    private final List<SseEmitter> emitterList = new CopyOnWriteArrayList<>();
    private final PartyService partyService;
    private final MemberService memberService;

    public SseEmitters (PartyService partyService, MemberService memberService){
        this.partyService = partyService;
        this.memberService = memberService;
    }

    static int currentStageCnt = 0;
    static int waitingListCnt = 0;
    public SseEmitter add(SseEmitter emitter){
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
        System.out.println("The TTTT : "+ this.emitterList.get(2));
        switch (eventName){
            case "currentGameStage" :
                Integer value = partyService.getCurrentGameStageQuery();
                send (eventName, dataMap.mapOf("cnt", currentStageCnt++, "currentStage", value));
                break;
            case "initWaitingList" :
                List<String> waitingList = memberService.getWaitingList();
                send (eventName, dataMap.mapOf("cnt", waitingListCnt++, "list", waitingList));
                //remove("initWaitList");
                break;
            default :
                break;
        }
    }

    public void send (String eventName, Map<String, Integer> data ){
        emitterList.forEach( emitter->{
            try {
                emitter.send(SseEmitter.event()
                        .name(eventName)
                        .data(data)
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void remove(String eventName) {
//        emitterList.forEach ( emitter->{
//            try {
//                emitter.send(SseEmitter.event()
//                        .id())
//            }
//        })
        if (!this.emitterList.isEmpty())
            this.emitterList.clear();
    }
}
