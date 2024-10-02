package com.toyproject.hyeonworld.api.round.interfaces;

import com.toyproject.hyeonworld.api.round.event.ScoreEvent;
import com.toyproject.hyeonworld.api.round.event.ScoreEvent.Ranking;
import com.toyproject.hyeonworld.common.event.EventListener;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 10. 2.
 */
public interface ScoreEventListener extends EventListener {
  void handleScoreEvent(ScoreEvent event);
  void handleScoreRankingEvent(Ranking event);
}
