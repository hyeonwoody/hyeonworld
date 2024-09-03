package com.toyproject.hyeonworld.api.session.event;

import com.toyproject.hyeonworld.common.event.CustomEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 4.
 */
@Getter
@RequiredArgsConstructor
public class LoginEvent implements CustomEvent {
  private final long userId;
  private final String userName;
}
