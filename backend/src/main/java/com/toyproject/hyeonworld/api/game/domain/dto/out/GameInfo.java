package com.toyproject.hyeonworld.api.game.domain.dto.out;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.toyproject.hyeonworld.api.game.infrastructure.entity.Game;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
@Getter
public class GameInfo {
    String name;
    String description;
  public static GameInfo from (Game game) {
    return ObjectrMapper.convert(game, GameInfo.class);
  }
}
