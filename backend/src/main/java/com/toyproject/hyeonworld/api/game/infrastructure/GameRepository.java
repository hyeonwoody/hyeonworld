package com.toyproject.hyeonworld.api.game.infrastructure;

import com.toyproject.hyeonworld.api.game.domain.dto.out.GameInfos;
import com.toyproject.hyeonworld.api.game.infrastructure.entity.Game;
import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public interface GameRepository {

  List<Game> findByPlayable(boolean playable);

  List<Game> findByPlayableJpa(boolean b);
}
