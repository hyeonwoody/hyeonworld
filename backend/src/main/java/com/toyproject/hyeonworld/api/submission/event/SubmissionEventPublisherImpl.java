package com.toyproject.hyeonworld.api.submission.event;

import com.toyproject.hyeonworld.common.event.CustomEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
* @author : hyeonwoody@gmail.com
* @since : 24. 9. 7.
*/
@Component
@RequiredArgsConstructor
public class SubmissionEventPublisherImpl implements SubmissionEventPublisher{
  private final ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void execute(CustomEvent event) {
    applicationEventPublisher.publishEvent(event);
  }
}
