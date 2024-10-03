package com.toyproject.hyeonworld.api.round.domain.dto.out;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 16.
 */
@Getter
public class ResultInfo {
  private final String answer;
  private final List<Winner> winners;

  @Getter
  public static class Winner {
    private final long id;
    private final String name;

    public Winner(long id, String name) {
      this.id = id;
      this.name = name;
    }
  }

  public ResultInfo(String answer) {
    this.answer = answer;
    this.winners = new ArrayList<>();
  }

  public void addWinner(long userId, String userName) {
    Winner winner = new Winner(userId, userName);
    winners.add(winner);
  }
}
