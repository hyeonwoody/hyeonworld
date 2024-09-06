package com.toyproject.hyeonworld.api.party.infrastructure.jdbc;

import com.toyproject.hyeonworld.DTO.PartyInitDTO;
import com.toyproject.hyeonworld.api.party.infrastructure.entity.Party;
import jakarta.persistence.Column;
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
public class PartyJdbcTemplateRepository {
  private final JdbcTemplate jdbcTemplate;

  public Party insert(Party party) {
    String sql = "INSERT INTO party (relation_type, created_at) " +
        "VALUES (?, ?)";
    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      ps.setByte(1, party.getRelationType());
      ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
      return ps;
    }, keyHolder);

    Number key = keyHolder.getKey();
    if (key != null) {
      party.setId(key.longValue());
    }
    return party;
  }
  public Party update(Party party) {
    StringBuilder sql = new StringBuilder("UPDATE party SET");
    List<Object> params = new ArrayList<>();
    boolean needComma = false;
    if (party.getRelationType() != null){
      sql.append(" reation_type = ?");
      params.add(party.getRelationType());
      needComma = true;
    }
    if (party.getTerminatedAt() != null) {
      if (needComma) {
        sql.append(",");
      }
      sql.append(" terminated_at = ?");
      params.add(party.getTerminatedAt());
      needComma = true;
    }
    if (params.isEmpty()) {
      return party;
    }

    sql.append(" WHERE id = ?");
    params.add(party.getId());
    try {
      int updatedRows = jdbcTemplate.update(sql.toString(),
          params.toArray());

      if (updatedRows == 0) {
      }
      return party;
    } catch (DataAccessException e) {
      throw new RuntimeException("Failed to update party", e);
    }
  }

  public Party save(Party party) {
    return Optional.ofNullable(party.getId())
        .map(id -> update(party))
        .orElseGet(() -> insert(party));
  }
}
