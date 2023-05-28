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
    public ResponseEntity<Integer> setGameStage(@RequestParam Integer currentStage) {

        Integer ret = partyService.setCurrentGameStage(currentStage);

        threadService.executorService.execute(()->{
            sseEmitters.send("currentGameStage");
        });

        return ResponseEntity.ok (ret);

    }

    @GetMapping(value = "/game-stage", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> getGameStage(@RequestParam Long memberId) throws InterruptedException {
        /*
        Singleprocess
         */
        System.out.println("Players");

        /*
        Init Waiting List.
         */
        CustomSseEmitter emitter = new CustomSseEmitter ();
        sseEmitters.add(emitter);

        return ResponseEntity.ok(emitter);
    }

    @GetMapping(value = "/game-stage/init")
    public ResponseEntity<Integer> initGameStage() {
        Integer ret = partyService.getCurrentGameStage();
        return ResponseEntity.ok(ret);
    }
}
