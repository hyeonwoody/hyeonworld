package com.toyproject.hyeonworld.api.submission.infrastructure.jdbc;

import com.toyproject.hyeonworld.api.submission.infrastructure.AnswerSubmissionRepository;
import com.toyproject.hyeonworld.api.submission.infrastructure.entity.AnswerSubmission;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 16.
 */
@Repository
@RequiredArgsConstructor
public class AnswerSubmissionJdbcTemplateRepositoryImpl implements AnswerSubmissionJdbctemplateRepository {
  private final JdbcTemplate jdbcTemplate;


  public List<AnswerSubmission> findAnswerMostRecentByRoundId(long roundId) {
    String sql = """
            SELECT s.*
            FROM answer_submission s
            INNER JOIN (
                SELECT user_id, MAX(id) as max_id
                FROM answer_submission
                WHERE round_id = ?
                GROUP BY user_id
            ) latest ON s.user_id = latest.user_id AND s.id = latest.max_id
            WHERE s.round_id = ?
        """;
    return jdbcTemplate.query(
        sql,
        new Long[]{roundId, roundId},
        (resultSet, rowNum) -> AnswerSubmission.createForResult(resultSet));
  }

  @Override
  public AnswerSubmission saveAnswer(AnswerSubmission answerSubmission) {
    return null;
  }
}
