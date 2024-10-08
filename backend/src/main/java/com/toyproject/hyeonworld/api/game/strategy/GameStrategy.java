package com.toyproject.hyeonworld.api.game.strategy;

import com.toyproject.hyeonworld.api.game.strategy.dto.StringOrLong;
import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundPlayCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.in.SubmissionCheckConfirmCommand;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 11.
 */
public interface GameStrategy {
  long getGameId();
  StringOrLong<?> checkConfirm(SubmissionCheckConfirmCommand command);


  String show(long roundId);

  long play(RoundPlayCommand command);
}
