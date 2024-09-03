package com.toyproject.hyeonworld.api.game.infrastructure.jdbcTemplate;

import com.toyproject.hyeonworld.api.game.infrastructure.entity.Game;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
@Repository
@RequiredArgsConstructor
public class GameJdbcTemplateRepositoryImpl implements GameJdbcTemplateRepository {
  private final JdbcTemplate jdbcTemplate;

  @Override
  public List<Game> findByPlayble(boolean playable) {
    String sql = "SELECT name, description FROM game WHERE playable = ?";
    return this.jdbcTemplate.query(
        sql,
        new Boolean[]{playable},
        (resultSet, rowNum) -> Game.createToDisplay(resultSet)
    );
  }


}
