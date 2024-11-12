package com.toyproject.hyeonworld.api.user.application.dto;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 10. 1.
 */
public record NameScoreDto(
        String name,
        long score
        ){

  public NameScoreDto(String name, long score) {
    this.name = name;
    this.score = score;
  }

  public static NameScoreDto from (String name, long score) {
    return new NameScoreDto(name, score);
  }
}
