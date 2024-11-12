package com.toyproject.hyeonworld.api.round.domain.dto.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.checkerframework.checker.units.qual.A;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 10. 1.
 */
public record UserNameScoreInfo (
        String name,
        long score
        ){

  public UserNameScoreInfo(String name, long score) {
    this.name = name;
    this.score = score;
  }

  public static UserNameScoreInfo from (String name, long score) {
    return new UserNameScoreInfo(name, score);
  }
}
