package com.toyproject.hyeonworld.api.game.controller.dto.res;

import com.toyproject.hyeonworld.api.game.domain.dto.out.GameInfos;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public record DisplayGameResponse (List<GameResponse> games) {

  public static DisplayGameResponse from (GameInfos gameInfos){
    List<GameResponse> gameResponses = gameInfos.stream()
        .map(GameResponse::from)
        .collect(Collectors.toList());
    return new DisplayGameResponse(gameResponses);
  }

}
