package com.toyproject.hyeonworld.controller;

import com.toyproject.hyeonworld.controller.sse.CustomSseEmitter;
import com.toyproject.hyeonworld.controller.sse.DataMap;
import com.toyproject.hyeonworld.controller.sse.SseEmitters;
import com.toyproject.hyeonworld.service.PartyService;
import com.toyproject.hyeonworld.service.ThreadService;
import jakarta.servlet.http.HttpServlet;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
@RequestMapping("/api")
public class GameStageController extends HttpServlet {

    private PartyService partyService;
    private final ThreadService threadService;

    private static final long SSE_SESSION_TIMEOUT = 1500L;




    private class GameStageEmitters extends SseEmitters {
        @Override
        public void send (String eventName){
            DataMap dataMap = new DataMap();
            Integer currentGameStage = partyService.getCurrentGameStageQuery();
            send (eventName, dataMap.mapOf("gameStage", currentGameStage));
        }
    }

    private final GameStageEmitters sseEmitters = new GameStageEmitters();

    public GameStageController(PartyService partyService, ThreadService threadService) {
        this.threadService = threadService;
        this.partyService = partyService;
    }

    @GetMapping(value = "/empty")
    public ResponseEntity<Boolean> empty() {
        return ResponseEntity.ok(true);
    }

    @PutMapping(value = "/game-stage")
    public ResponseEntity<Integer> setStage(@RequestParam Integer currentStage) {
        System.out.println("rrrrrrrr");

        Integer ret = partyService.setCurrentGameStage(currentStage);

        threadService.executorService.execute(()->{
            sseEmitters.send("currentGameStage");
        });

        return ResponseEntity.ok (ret);

    }

    @GetMapping(value = "/game-stage", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> getStage(@RequestParam Long memberId) throws InterruptedException {
        /*
        Singleprocess
         */
        System.out.println("Players");
        CustomSseEmitter emitter = new CustomSseEmitter ();
        sseEmitters.add(emitter);

        return ResponseEntity.ok(emitter);
    }
}
