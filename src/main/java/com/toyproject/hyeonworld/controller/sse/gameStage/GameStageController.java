package com.toyproject.hyeonworld.controller.sse.gameStage;

import com.toyproject.hyeonworld.controller.sse.SseEmitters;
import jakarta.servlet.http.HttpServlet;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
@RequestMapping("/api")
public class GameStageController extends HttpServlet {

    private final SseEmitters sseEmitters;
    static AtomicInteger counter;

    public GameStageController(SseEmitters sseEmitters) {
        this.sseEmitters = sseEmitters;
        this.counter = new AtomicInteger(0);
    }

    @GetMapping(value = "/gameStage", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect() throws InterruptedException {



        ExecutorService executor = Executors.newFixedThreadPool(20); //Number of family members.
        SseEmitter emitter = new SseEmitter();
        StopWatch main = new StopWatch();
        main.start();
        for (int i = 0; i < 20; ++i){
            executor.execute(()->{
                int cnt = counter.addAndGet(1);
                sseEmitters.add(emitter);
                try{

                    emitter.send(SseEmitter.event()
                            .name("connect")
                            .data(cnt));
                    System.out.println("보냅");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        executor.shutdown();
        executor.awaitTermination(20, TimeUnit.SECONDS);
        main.stop();

        // Start an asynchronous task to send SSE events
        // You can customize this task based on your requirements
        return ResponseEntity.ok(emitter);
    }
}
