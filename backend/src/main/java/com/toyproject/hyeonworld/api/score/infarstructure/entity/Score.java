package com.toyproject.hyeonworld.api.score.infarstructure.entity;

import com.toyproject.hyeonworld.api.score.infarstructure.entity.ScoreHistory.ScoreHistoryBuilder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "score", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"party_id", "user_id"})
})
public class Score {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "party_id")
  private Long partyId;

  @Column(name = "user_id")
  private Long userId;

  private Long score;

  public static ScoreBuilder defaultBuilder(){
    return Score.builder();
  }

  public static Score create(long partyId, long userId, long score) {
    return defaultBuilder()
        .partyId(partyId)
        .userId(userId)
        .score(score)
        .build();
  }
}