package com.toyproject.hyeonworld.api.game.strategy;

import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundPlayCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.in.SubmissionCheckConfirmCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.out.RoundInfo;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 11.
 */
public interface GameStrategy<AnswerType> {
  long getGameId();
  AnswerType checkConfirm(SubmissionCheckConfirmCommand command);
  RoundInfo updateRoundAnswer(long roundId, AnswerType answer);
  void tutorialStage();
  String showStage(long roundId);
  AnswerType playStage(RoundPlayCommand command);
}
