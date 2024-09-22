package com.toyproject.hyeonworld.api.score.domain.dto.out;

import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundPlayCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundResultConfirmCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundResultConfirmCommand.Winner;
import com.toyproject.hyeonworld.api.score.infarstructure.entity.ScoreHistory;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
public class ScoreHistoryInfo {
  Long userId;
  Long partyId;
  Long roundId;
  String answer;
  Long score;

  public static ScoreHistory createEntity(RoundPlayCommand command, long roundId, String answer) {
    return ScoreHistory.builder()
        .partyId(command.partyId())
        .roundId(roundId)
        .userId(command.userId())
        .answer(command.answer())
        .score(answer.equals(command.answer())? 1L : 0L)
        .build();
  }

  public static List<ScoreHistory> createEntities(RoundResultConfirmCommand command) {
    List<ScoreHistory> scoreHistories = new ArrayList<>();
    for (Winner winner : command.winners()) {
      ScoreHistory scoreHistory = ScoreHistory.builder()
          .partyId(command.partyId())
          .roundId(command.roundId())
          .userId(winner.id())
          .score(winner.score())
          .build();
      scoreHistories.add(scoreHistory);
    }

    return scoreHistories;
  }
}