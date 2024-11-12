package com.toyproject.hyeonworld.api.round.domain.dto.out;

import com.toyproject.hyeonworld.api.user.application.dto.NameDtos;
import java.util.Map;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 16.
 */
public record ResultStage(
    String answer,
    NameDtos nameDtos
){

  public ResultStage(String answer, NameDtos nameDtos) {
    this.answer = answer;
    this.nameDtos = nameDtos;
  };

  public static ResultStage from(String answer, Map<Long, String> winnersIdName) {
    return new ResultStage(answer, NameDtos.from(winnersIdName));
  }
}
