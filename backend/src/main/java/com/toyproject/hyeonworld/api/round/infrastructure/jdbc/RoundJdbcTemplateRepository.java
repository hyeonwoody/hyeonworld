package com.toyproject.hyeonworld.api.round.infrastructure.jdbc;

import com.toyproject.hyeonworld.api.game.infrastructure.entity.Game;
import com.toyproject.hyeonworld.api.party.infrastructure.entity.Party;
import com.toyproject.hyeonworld.api.round.infrastructure.entity.Round;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 6.
 */
@Repository
@RequiredArgsConstructor
public class RoundJdbcTemplateRepository {
  private final JdbcTemplate jdbcTemplate;

  public Round insert(Round round) {
    String sql = "INSERT INTO round (party_id, game_id) " +
        "VALUES (?, ?)";
    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      ps.setLong(1, round.getPartyId());
      ps.setLong(2, round.getGameId());
      return ps;
    }, keyHolder);

    Number key = keyHolder.getKey();
    if (key != null) {
      round.setId(key.longValue());
    }
    return round;
  }

  public Round update(Round round) {
    StringBuilder sql = new StringBuilder("UPDATE round SET");
    List<Object> params = new ArrayList<>();
    boolean needComma = false;
    if (round.getAnswer() != null){
      sql.append(" answer = ?");
      params.add(round.getAnswer());
      needComma = true;
    }

    if (params.isEmpty()) {
      return round;
    }

    sql.append(" WHERE id = ?");
    params.add(round.getId());
    try {
      int updatedRows = jdbcTemplate.update(sql.toString(),
          params.toArray());

      if (updatedRows == 0) {
      }
      return round;
    } catch (DataAccessException e) {
      throw new RuntimeException("Failed to update round", e);
    }
  }

  public Round save(Round round) {
    return Optional.ofNullable(round.getId())
        .map(id -> update(round))
        .orElseGet(() -> insert(round));
  }

  public List<Game> findByPlayble(boolean playable) {
    String sql = "SELECT name, description FROM game WHERE playable = ?";
    return this.jdbcTemplate.query(
        sql,
        new Boolean[]{playable},
        (resultSet, rowNum) -> Game.createToDisplay(resultSet)
    );
  }

  public Optional<Round> findByPartyId(long partyId) {
    String sql = "SELECT * FROM round WHERE party_id = ? ORDER BY created_at DESC LIMIT 1";
    return jdbcTemplate.query(sql, this::mapRowToRound, partyId)
        .stream()
        .findFirst();
  }

  private Round mapRowToRound(ResultSet rs, int rowNum) throws SQLException {
    Round round = new Round();
    round.setId(rs.getLong("id"));
    round.setPartyId(rs.getLong("party_id"));
    round.setGameId(rs.getLong("game_id"));
    round.setAnswer(rs.getString("answer"));
    round.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
    return round;
  }
}
