package com.toyproject.hyeonworld.api.submission.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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

  @PrePersist
  public void onCreate() {
    this.createdAt = LocalDateTime.now();
  }
}