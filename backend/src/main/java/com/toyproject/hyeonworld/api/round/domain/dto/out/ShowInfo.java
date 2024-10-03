package com.toyproject.hyeonworld.api.round.domain.dto.out;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
@Getter
@AllArgsConstructor
public class ShowInfo {
  private String content;

  public static ShowInfo from(String content) {
    return new ShowInfo(content);
  }
}
