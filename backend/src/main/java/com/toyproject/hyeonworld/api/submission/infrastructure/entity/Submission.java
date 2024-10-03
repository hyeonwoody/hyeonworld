package com.toyproject.hyeonworld.api.submission.infrastructure.entity;

import com.toyproject.hyeonworld.api.game.infrastructure.entity.Game;
import com.toyproject.hyeonworld.api.game.infrastructure.entity.Game.GameBuilder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
* @author : hyeonwoody@gmail.com
* @since : 24. 9. 7.
*/
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "submission", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"round_id", "user_id", "created_at"})
})
public class Submission {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "round_id")
  private Long roundId;

  @Column(name = "user_id")
  private Long userId;

  private Long number;

  @Column(length = 255)
  private String text;

  @Column(name = "created_at")
  private LocalDateTime createdAt;
  public static SubmissionBuilder defaultBuilder(){
    return Submission.builder();
  }

  public static Submission createToShow(ResultSet rs) throws SQLException {
    return defaultBuilder()
        .id(rs.getLong("id"))
        .roundId(rs.getLong("round_id"))
        .userId(rs.getLong("user_id"))
        .text(rs.getString("text"))
        .number(rs.getLong("number"))
        .build();
  }

  public static Submission createToCheck(ResultSet rs) throws SQLException {
    return defaultBuilder()
        .userId(rs.getLong("user_id"))
        .text(rs.getString("text"))
        .number(rs.getLong("number"))
        .build();
  }

  @PrePersist
  public void onCreate() {
    this.createdAt = LocalDateTime.now();
  }
}