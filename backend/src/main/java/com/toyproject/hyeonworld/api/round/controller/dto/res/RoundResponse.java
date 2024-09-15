package com.toyproject.hyeonworld.api.round.controller.dto.res;

import com.toyproject.hyeonworld.api.round.domain.dto.out.PlayInfo;
import com.toyproject.hyeonworld.api.round.domain.dto.out.ResultInfo;
import com.toyproject.hyeonworld.api.round.domain.dto.out.RoundInfo;

import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;
import java.util.List;


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
  record Play (
      long userId
  ) {
    public static RoundResponse.Play from (PlayInfo playInfo){
      return ObjectrMapper.convert(playInfo, Play.class);
    }
  }

  record Result (
      String answer,
      List<String> winners
  ) {
    public static RoundResponse.Result from (ResultInfo resultInfo){
      return ObjectrMapper.convert(resultInfo, Result.class);
    }
  }

}
