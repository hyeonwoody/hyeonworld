package com.toyproject.hyeonworld.api.game.domain.dto.out;

import com.toyproject.hyeonworld.api.game.infrastructure.entity.Game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public class GameInfos extends ArrayList<GameInfo> {

  public GameInfos(List<GameInfo> gameInfos) {
    super(gameInfos != null ? gameInfos : Collections.emptyList());
  }

  public static GameInfos from(List<Game> games) {
    return new GameInfos(games.stream()
        .map(GameInfo::from)
        .collect(Collectors.toList()));
  }
}
