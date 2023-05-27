package com.toyproject.hyeonworld.controller.sse.gameStage;

import com.toyproject.hyeonworld.controller.sse.CustomSseEmitter;
import com.toyproject.hyeonworld.controller.sse.SseEmitters;
import com.toyproject.hyeonworld.service.PartyService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


import java.util.concurrent.atomic.AtomicInteger;


@RestController
@RequestMapping("/api")
public class GameStageController extends HttpServlet {

    private PartyService partyService;

    private static final long SSE_SESSION_TIMEOUT = 1500L;

    private final SseEmitters sseEmitters;
    static AtomicInteger counter;

    public GameStageController(SseEmitters sseEmitters, PartyService partyService) {
        this.sseEmitters = sseEmitters;
        this.counter = new AtomicInteger(0);
        this.partyService = partyService;
    }

    @GetMapping(value = "/empty")
    public ResponseEntity<Boolean> empty() {

        return ResponseEntity.ok(true);
    }

    @PutMapping(value = "/game-stage")
    public ResponseEntity<Integer> setStage(@RequestParam Integer currentStage) {
        System.out.println("rrrrrrrr");
        partyService.setCurrentGameStage(currentStage);
        return ResponseEntity.ok (currentStage);
    }

    @GetMapping(value = "/game-stage", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> getStage(@RequestParam Long memberId) throws InterruptedException {
        /*
        Singleprocess
         */
        System.out.println("Players");
        CustomSseEmitter emitter = new CustomSseEmitter (SSE_SESSION_TIMEOUT);
        sseEmitters.add(emitter);
        sseEmitters.send ("currentGameStage");

        return ResponseEntity.ok(emitter);
    }
}
