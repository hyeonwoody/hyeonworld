package com.toyproject.hyeonworld.api.round.controller.dto.res;

import com.toyproject.hyeonworld.api.round.domain.dto.out.RoundInfo;

import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;


/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 6.
 */
public abstract interface RoundResponse {
  long getId();

  record Begin (
      long id
  ) {
    public static RoundResponse.Begin from (RoundInfo roundInfo){
      return ObjectrMapper.convert(roundInfo, Begin.class);
    }
  }
  record Answer (
      long id,
      int answer
  ) {
    public static RoundResponse.Answer from (RoundInfo roundInfo){
      return ObjectrMapper.convert(roundInfo, Answer.class);
    }
  }
}
