package com.toyproject.hyeonworld.api.session.event;

import com.toyproject.hyeonworld.common.event.CustomEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 4.
 */
@Component
@RequiredArgsConstructor
public class LoginEventPublisherImpl implements LoginEventPublisher{
  private final ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void execute(CustomEvent loginEvent) {
    applicationEventPublisher.publishEvent(loginEvent);
  }


}
