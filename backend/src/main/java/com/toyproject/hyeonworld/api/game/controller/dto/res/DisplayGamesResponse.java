package com.toyproject.hyeonworld.api.game.controller.dto.res;

import com.toyproject.hyeonworld.api.game.domain.dto.out.GameInfos;
import com.toyproject.hyeonworld.entity.Game;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public record DisplayGamesResponse(GameInfos games) implements GameResponse {

  public static DisplayGamesResponse from (GameInfos gameInfos){
    return new DisplayGamesResponse(gameInfos);
  }
}
