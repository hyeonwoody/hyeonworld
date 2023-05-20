package com.toyproject.hyeonworld.controller.sse.gameStage;

import com.toyproject.hyeonworld.controller.sse.SseEmitters;
import jakarta.servlet.http.HttpServlet;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class GameStageController extends HttpServlet {

    private final SseEmitters sseEmitters;

    public GameStageController(SseEmitters sseEmitters) {
        this.sseEmitters = sseEmitters;
    }

    @GetMapping(value = "/gameStage", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect() {

        SseEmitter emitter = new SseEmitter();
        sseEmitters.add(emitter);
        try{

            emitter.send(SseEmitter.event()
                    .name("connect")
                    .data("Connected!"));
            System.out.println("보냅");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Start an asynchronous task to send SSE events
        // You can customize this task based on your requirements
        return ResponseEntity.ok(emitter);
    }
}
