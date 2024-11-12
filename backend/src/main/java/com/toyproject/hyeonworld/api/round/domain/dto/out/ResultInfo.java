package com.toyproject.hyeonworld.api.round.domain.dto.out;

import java.util.Map;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 16.
 */
public record ResultInfo (
    String answer,
    UserNameInfos userNameInfos
){

  public ResultInfo(String answer, UserNameInfos userNameInfos) {
    this.answer = answer;
    this.userNameInfos = userNameInfos;
  };

  public static ResultInfo from(String answer, Map<Long, String> winnersIdName) {
    return new ResultInfo(answer, UserNameInfos.from(winnersIdName));
  }
}
