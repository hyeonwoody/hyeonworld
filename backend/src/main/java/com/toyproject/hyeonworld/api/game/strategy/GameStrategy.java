package com.toyproject.hyeonworld.api.game.strategy;

import com.toyproject.hyeonworld.api.game.strategy.dto.StringOrLong;
import com.toyproject.hyeonworld.api.round.domain.dto.in.SubmissionCheckConfirmCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.out.ShowInfo;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 11.
 */
public interface GameStrategy {
  long getGameId();
  StringOrLong<?> checkConfirm(SubmissionCheckConfirmCommand command);


  String show(long roundId);
}
