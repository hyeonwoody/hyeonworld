package com.toyproject.hyeonworld.api.submission.infrastructure.jdbc;

import com.toyproject.hyeonworld.api.game.infrastructure.entity.Game;
import com.toyproject.hyeonworld.api.submission.infrastructure.entity.Submission;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 8.
 */
@Repository
@RequiredArgsConstructor
public class SubmissionJdbcTemplateRepository {
  private final JdbcTemplate jdbcTemplate;



  public List<Submission> findMostRecentByRoundId(long roundId) {
    String sql = """
            SELECT s.*
            FROM submission s
            INNER JOIN (
                SELECT user_id, MAX(created_at) as max_created_at
                FROM submission
                WHERE round_id = ?
                GROUP BY user_id
            ) latest ON s.user_id = latest.user_id AND s.created_at = latest.max_created_at
            WHERE s.round_id = ?
        """;
    return jdbcTemplate.query(
        sql,
        new Long[]{roundId, roundId},
        (resultSet, rowNum) -> Submission.createToCheck(resultSet));
  }

  public Submission findMostRecentByUserId(long userId) {
    String sql = """
            SELECT s.*
            FROM submission s
            INNER JOIN (
                SELECT MAX(created_at) as max_created_at
                FROM submission
                WHERE user_id = ?
            ) latest ON s.created_at = latest.max_created_at
            WHERE s.user_id = ?
            LIMIT 1
        """;
    return jdbcTemplate.queryForObject(
        sql,
        new Object[]{userId, userId},
        (resultSet, rowNum) -> Submission.createToShow(resultSet));
  }
}
