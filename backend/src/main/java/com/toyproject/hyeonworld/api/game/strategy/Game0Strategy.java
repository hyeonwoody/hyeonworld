package com.toyproject.hyeonworld.api.game.strategy;

import com.toyproject.hyeonworld.api.game.strategy.dto.StringOrLong;
import com.toyproject.hyeonworld.api.round.domain.dto.in.SubmissionCheckConfirmCommand;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionCheckInfo;
import com.toyproject.hyeonworld.common.annotation.Strategy;
import org.springframework.stereotype.Component;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 11.
 */
@Strategy(0)
public class Game0Strategy implements GameStrategy{

  @Override
  public long getGameId() {
    return 0L;
  }

  @Override
  public StringOrLong<Long> checkConfirm(SubmissionCheckConfirmCommand command) {
    return StringOrLong.ofNumber(command.number());
  }
}
