package com.toyproject.hyeonworld.api.round.infrastructure.jdbc;

import com.toyproject.hyeonworld.api.party.infrastructure.entity.Party;
import com.toyproject.hyeonworld.api.round.infrastructure.entity.Round;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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
}
