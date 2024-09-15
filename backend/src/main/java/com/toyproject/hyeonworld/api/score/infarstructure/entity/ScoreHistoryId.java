package com.toyproject.hyeonworld.api.score.infarstructure.entity;

import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* @author : hyeonwoody@gmail.com
* @since : 24. 9. 14.
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class ScoreHistoryId implements Serializable {
  private Long userId;
  private Long partyId;
  private Long roundId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ScoreHistoryId that = (ScoreHistoryId) o;
    return Objects.equals(userId, that.userId) &&
        Objects.equals(partyId, that.partyId) &&
        Objects.equals(roundId, that.roundId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, partyId, roundId);
  }
}