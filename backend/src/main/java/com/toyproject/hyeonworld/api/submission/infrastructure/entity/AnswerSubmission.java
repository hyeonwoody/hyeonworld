package com.toyproject.hyeonworld.api.submission.infrastructure.entity;

import com.toyproject.hyeonworld.api.submission.infrastructure.entity.Submission.SubmissionBuilder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "answer_submission")
public class AnswerSubmission {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "round_id")
  private Long roundId;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "answer")
  private String answer;

  public static AnswerSubmissionBuilder defaultBuilder(){
    return AnswerSubmission.builder();
  }

  public static AnswerSubmission createForResult(ResultSet rs) throws SQLException {
    return defaultBuilder()
        .id(rs.getLong("id"))
        .roundId(rs.getLong("round_id"))
        .userId(rs.getLong("user_id"))
        .answer(rs.getString("answer"))
        .build();
  }
}
