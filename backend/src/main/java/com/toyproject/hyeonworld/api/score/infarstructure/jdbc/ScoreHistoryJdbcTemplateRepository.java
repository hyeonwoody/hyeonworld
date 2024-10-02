package com.toyproject.hyeonworld.api.score.infarstructure.jdbc;

import com.toyproject.hyeonworld.api.round.infrastructure.entity.Round;
import com.toyproject.hyeonworld.api.score.infarstructure.entity.Score;
import com.toyproject.hyeonworld.api.score.infarstructure.entity.ScoreHistory;
import com.toyproject.hyeonworld.api.submission.infrastructure.entity.Submission;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 21.
 */
@Repository
@RequiredArgsConstructor
public class ScoreHistoryJdbcTemplateRepository {
  private final JdbcTemplate jdbcTemplate;

  protected List<ScoreHistory> fetchScoreHistories(List<ScoreHistory> scoreHistories) {
    if (scoreHistories.isEmpty()) {
      return new ArrayList<>();
    }

    String placeholders = String.join(",", Collections.nCopies(scoreHistories.size(), "(?,?,?)"));
    String selectSql = "SELECT user_id, party_id, round_id, answer, score FROM score_history " +
        "WHERE (user_id, party_id, round_id) IN (" + placeholders + ")";

    List<Object> params = new ArrayList<>();
    for (ScoreHistory history : scoreHistories) {
      params.add(history.getUserId());
      params.add(history.getPartyId());
      params.add(history.getRoundId());
    }

    return jdbcTemplate.query(selectSql, params.toArray(), this::mapRowToScoreHistory);
  }

  public List<ScoreHistory> findByPartyId(long partyId) {
    String sql = """
            SELECT s.*
            FROM score_history s
            WHERE s.party_id = ?
        """;
    return jdbcTemplate.query(
        sql,
        new Long[]{partyId},
        (resultSet, rowNum) -> ScoreHistory.createForRanking(resultSet));
  }



  public List<ScoreHistory> saveAll(List<ScoreHistory> scoreHistories) {
    String sql = "INSERT INTO score_history (user_id, party_id, round_id, answer, score) " +
        "VALUES (?, ?, ?, ?, ?) " +
        "ON DUPLICATE KEY UPDATE " +
        "answer = VALUES(answer), score = VALUES(score)";

    jdbcTemplate.batchUpdate(sql, scoreHistories, scoreHistories.size(),
        (PreparedStatement ps, ScoreHistory scoreHistory) -> {
          ps.setLong(1, scoreHistory.getUserId());
          ps.setLong(2, scoreHistory.getPartyId());
          ps.setLong(3, scoreHistory.getRoundId());
          ps.setString(4, scoreHistory.getAnswer());
          ps.setLong(5, scoreHistory.getScore());
        });
    return fetchScoreHistories(scoreHistories);
  }

  private ScoreHistory mapRowToScoreHistory(ResultSet rs, int rowNum) throws SQLException {
    ScoreHistory scoreHistory = new ScoreHistory();
    scoreHistory.setUserId(rs.getLong("user_id"));
    scoreHistory.setPartyId(rs.getLong("party_id"));
    scoreHistory.setRoundId(rs.getLong("round_id"));
    scoreHistory.setAnswer(rs.getString("answer"));
    scoreHistory.setScore(rs.getLong("score"));
    return scoreHistory;
  }


}
