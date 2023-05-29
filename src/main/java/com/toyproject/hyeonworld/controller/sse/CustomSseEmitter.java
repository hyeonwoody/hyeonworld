package com.toyproject.hyeonworld.controller.sse;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Set;

public class CustomSseEmitter extends SseEmitter{
    private Long id;
    private String eventName;
    public CustomSseEmitter() {
        super();
    }

    public CustomSseEmitter(Long id, String eventName) {
        super();
        this.id = id;
        this.eventName = eventName;
    }


    public CustomSseEmitter(Long timeout) {super(timeout);}
    public CustomSseEmitter(Long timeout, Long id) {
        super(timeout);
        this.id = id;
    }
    public CustomSseEmitter(Long timeout, Long id, String eventName) {
        super(timeout);
        this.id = id;
        this.eventName = eventName;
    }

    public Long getId(){
        return this.id;
    }

    public String getEventName() {
        return this.eventName;
    }

    public void onError() {
    }


}
