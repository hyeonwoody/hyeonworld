package com.toyproject.hyeonworld.api.sse.interfaces;

import lombok.Getter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 4.
 */
@Getter
public class CustomEmitter extends SseEmitter {
  protected Long id;
  protected CustomEmitter() {
    super();
  }

  protected CustomEmitter(Long id) {
    super();
    this.id = id;
  }

  public static CustomEmitter from(long userId) {
    return new CustomEmitter(userId);
  }
}
