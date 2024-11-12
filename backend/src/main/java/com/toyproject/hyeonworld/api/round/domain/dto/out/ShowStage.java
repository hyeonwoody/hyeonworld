package com.toyproject.hyeonworld.api.round.domain.dto.out;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
@Getter
@AllArgsConstructor
public class ShowStage {
  private String content;

  public static ShowStage from(String content) {
    return new ShowStage(content);
  }
}
