package com.toyproject.hyeonworld.controller;

import com.toyproject.hyeonworld.controller.sse.CustomSseEmitter;
import com.toyproject.hyeonworld.controller.sse.SseEmitters;
import com.toyproject.hyeonworld.service.PartyService;
import com.toyproject.hyeonworld.service.ThreadService;
import jakarta.servlet.http.HttpServlet;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@RestController
@RequestMapping("/api")
public class GameStageController extends HttpServlet {

    private PartyService partyService;



    private final SseEmitters sseEmitters;

    public GameStageController(PartyService partyService, SseEmitters sseEmitters) {
        this.partyService = partyService;
        this.sseEmitters = sseEmitters;
    }

    @GetMapping(value = "/empty")
    public ResponseEntity<Boolean> empty() {
        return ResponseEntity.ok(true);
    }

    @PutMapping(value = "/game-stage")
    public ResponseEntity<Integer> setGameStage(@RequestParam Integer currentStage) {
        Integer ret = partyService.setCurrentGameStage(currentStage);
        if (!sseEmitters.empty())
            sseEmitters.send("currentGameStage");
        System.out.println("Game Stage 길이 : "+sseEmitters.size());
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
        CustomSseEmitter emitter = new CustomSseEmitter (memberId, "currentGameStage");
        sseEmitters.add(emitter);
        System.out.println("Game Stage 길이 : "+sseEmitters.size());

        return ResponseEntity.ok(emitter);
    }

    @GetMapping(value = "/game-stage/init")
    public ResponseEntity<Integer> initGameStage() {
        Integer ret = partyService.getCurrentGameStage();
        return ResponseEntity.ok(ret);
    }
}
