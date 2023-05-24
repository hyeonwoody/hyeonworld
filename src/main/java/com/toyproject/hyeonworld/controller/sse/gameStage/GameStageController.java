package com.toyproject.hyeonworld.controller.sse.gameStage;

import com.toyproject.hyeonworld.controller.sse.SseEmitters;
import com.toyproject.hyeonworld.service.PartyService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.Part;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
@RequestMapping("/api/game-stage")
public class GameStageController extends HttpServlet {

    private PartyService partyService;
    private static final long SSE_SESSION_TIMEOUT = 1000L;
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

    @PutMapping(value = "admin")
    public ResponseEntity<Integer> setStage(@RequestParam Integer currentStage) {
        System.out.println("rrrrrrrr");
        partyService.setCurrentGameStage(currentStage);
        return ResponseEntity.ok (currentStage);
    }

    @GetMapping(value = "/players", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect() throws InterruptedException {
        /*
        Singleprocess
         */
        SseEmitter emitter = new SseEmitter(SSE_SESSION_TIMEOUT);
        sseEmitters.add(emitter);
        sseEmitters.send ("currentGameStage");

        /*
        MultiThread
         */
//        ExecutorService executor = Executors.newFixedThreadPool(20);
//        SseEmitter emitter = new SseEmitter(SSE_SESSION_TIMEOUT);
//        sseEmitters.add(emitter);
//        sseEmitters.send ("currentGameStage");
//
//        StopWatch main = new StopWatch();
//        main.start();
//        for (int i = 0; i < 20; ++i){
//            executor.execute(()->{
//                int cnt = counter.addAndGet(1);
//                sseEmitters.add(emitter);
//                try{
//                    emitter.send(SseEmitter.event()
//                            .name("connect")
//                            .data(cnt));
//
////                    sseEmitters.send("currentGameStage");
//                    System.out.println("보냅");
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                } finally {
//                    System.out.println("emitter.complete()");
//                    emitter.complete();
//                }
//            });
//        }
//        executor.shutdown();
//        executor.awaitTermination(20, TimeUnit.SECONDS);
//        main.stop();

        // Start an asynchronous task to send SSE events
        // You can customize this task based on your requirements
        return ResponseEntity.ok(emitter);
    }
}
