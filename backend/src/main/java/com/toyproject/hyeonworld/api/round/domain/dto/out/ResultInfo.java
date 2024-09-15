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
  private final List<String> winners;


  public ResultInfo(String answer) {
    this.answer = answer;
    this.winners = new ArrayList<>();
  }

  public void addWinner(String userName) {
    winners.add(userName);
  }
}
