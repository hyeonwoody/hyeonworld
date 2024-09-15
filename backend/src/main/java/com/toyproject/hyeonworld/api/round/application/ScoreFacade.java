package com.toyproject.hyeonworld.api.round.application;

import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundPlayCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.out.PlayInfo;
import com.toyproject.hyeonworld.api.round.domain.dto.out.ShowInfo;
import com.toyproject.hyeonworld.api.score.domain.ScoreService;
import com.toyproject.hyeonworld.common.annotation.Facade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
@Facade
@RequiredArgsConstructor
public class ScoreFacade {
  private final ScoreService scoreService;

  public PlayInfo play(RoundPlayCommand command) {
    return null;
  }
}
