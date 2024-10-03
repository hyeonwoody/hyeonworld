package com.toyproject.hyeonworld.api.score.infarstructure.entity;

import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundPlayCommand;
import com.toyproject.hyeonworld.api.submission.infrastructure.entity.Submission;
import com.toyproject.hyeonworld.api.submission.infrastructure.entity.Submission.SubmissionBuilder;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
@Data
@Entity
@Table(name = "score_history",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "party_id", "round_id"}))
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ScoreHistory.class)
public class ScoreHistory {
  @Id
  @Column(name = "user_id")
  private Long userId;

  @Id
  @Column(name = "party_id")
  private Long partyId;

  @Id
  @Column(name = "round_id")
  private Long roundId;


  @Column(name = "answer")
  private String answer;

  @Column(name = "score")
  private Long score;

  public static ScoreHistoryBuilder defaultBuilder(){
    return ScoreHistory.builder();
  }

  public static ScoreHistory createForRanking(ResultSet rs) throws SQLException {
    return  defaultBuilder()
        .userId(rs.getLong("user_id"))
        .score(rs.getLong("score"))
        .build();
  }
}
