package com.toyproject.hyeonworld.api.game.controller.dto.res;

import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;
import com.toyproject.hyeonworld.api.game.domain.dto.out.GameInfo;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public record GameResponse(
    String name,
    String description
) {
  public static GameResponse from (GameInfo gameInfo){
    return ObjectrMapper.convert(gameInfo, GameResponse.class);
  }
}