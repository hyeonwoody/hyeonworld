package com.toyproject.hyeonworld.controller.sse;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Set;

public class CustomSseEmitter extends SseEmitter{
    private Long id;
    public CustomSseEmitter() {
        super();
    }
    public CustomSseEmitter(Long timeout) {super(timeout);}
    public CustomSseEmitter(Long timeout, Long id) {
        super(timeout);
        this.id = id;
    }

    public void onError() {
    }
}
