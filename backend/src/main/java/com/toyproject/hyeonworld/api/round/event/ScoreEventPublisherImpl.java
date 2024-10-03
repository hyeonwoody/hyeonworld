package com.toyproject.hyeonworld.api.round.event;

import com.toyproject.hyeonworld.common.event.CustomEvent;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 10. 2.
 */
@Component
@RequiredArgsConstructor
public class ScoreEventPublisherImpl implements ScoreEventPublisher{
  private final ApplicationEventPublisher applicationEventPublisher;
  @Override
  public void execute(CustomEvent event) {
    applicationEventPublisher.publishEvent(event);
  }
}
