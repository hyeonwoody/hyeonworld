package com.toyproject.hyeonworld.api.round.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 6.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Round {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "party_id")
  private Long partyId;

  @Column(name = "game_id")
  private Long gameId;

  private Integer answer;

  @Column(name = "created_at")
  private LocalDateTime createdAt;
}
