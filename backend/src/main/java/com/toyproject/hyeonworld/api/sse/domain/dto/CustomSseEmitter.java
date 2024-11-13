package com.toyproject.hyeonworld.api.sse.domain.dto;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 4.
 */
public class CustomSseEmitter extends SseEmitter {
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

  public Long getId(){
    return this.id;
  }

  public String getEventName() {
    return this.eventName;
  }

}
