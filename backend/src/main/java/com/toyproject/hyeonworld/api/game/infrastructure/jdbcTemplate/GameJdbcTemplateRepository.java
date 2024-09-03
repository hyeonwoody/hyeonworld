package com.toyproject.hyeonworld.api.game.infrastructure.jdbcTemplate;

import com.toyproject.hyeonworld.api.game.infrastructure.entity.Game;
import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public interface GameJdbcTemplateRepository {

  List<Game> findByPlayble(boolean playable);
}
