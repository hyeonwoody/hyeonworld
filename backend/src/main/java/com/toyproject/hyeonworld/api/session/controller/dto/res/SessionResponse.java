package com.toyproject.hyeonworld.api.session.controller.dto.res;

import org.springframework.http.ResponseEntity;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 4.
 */
public record SessionResponse(
    long userId
) {

  public static SessionResponse from(long userId) {
    return new SessionResponse(userId);
  }
}
