package com.toyproject.hyeonworld.api.round.domain.dto.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.checkerframework.checker.units.qual.A;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 10. 1.
 */
@Getter
@AllArgsConstructor
public class UserNameScoreInfo implements Comparable<UserScoreInfo>{
  private String name;
  private long score;

  @Override
  public int compareTo(UserScoreInfo u){
    return (int) (this.score - u.getScore());
  }
}
