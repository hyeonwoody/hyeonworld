package com.toyproject.hyeonworld.api.score.infarstructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
  private Long id;
  @Column(name = "party_id")
  private Long partyId;

  @Column(name = "user_id")
  private Long userId;

  private Long score;
}