package com.toyproject.hyeonworld.api.session.event;

import com.toyproject.hyeonworld.common.event.CustomEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 4.
 */
public sealed interface SessionEvent extends CustomEvent {
  long userId();
  record GameOut(long userId, String userName) implements SessionEvent {}
  record Login(long userId, String userName) implements SessionEvent {}

  record Logout(long userId) implements SessionEvent {}
  record GameIn(long userId, String userName) implements SessionEvent {}


}
