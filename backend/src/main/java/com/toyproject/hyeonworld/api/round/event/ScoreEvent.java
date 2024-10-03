package com.toyproject.hyeonworld.api.round.event;

import com.toyproject.hyeonworld.common.event.CustomEvent;
import java.util.Map;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 10. 2.
 */
public interface ScoreEvent extends CustomEvent {
  record Ranking (
      long partyId,
      Map<Long, Long> userScores) implements ScoreEvent{}
}
