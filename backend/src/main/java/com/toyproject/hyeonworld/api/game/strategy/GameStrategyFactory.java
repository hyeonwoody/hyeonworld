package com.toyproject.hyeonworld.api.game.strategy;

import com.toyproject.hyeonworld.common.annotation.StrategyFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 11.
 */
@StrategyFactory
public class GameStrategyFactory {
  private final Map<Long, GameStrategy> strategies;

  public GameStrategyFactory(List<GameStrategy> gameStrategies) {
    this.strategies = new HashMap<>();
    for (GameStrategy gameStrategy : gameStrategies){
      strategies.put(gameStrategy.getGameId(), gameStrategy);
    }
  }
  public GameStrategy getStrategy(long gameId) {
    GameStrategy strategy = strategies.get(gameId);
    if (strategy == null) {
      throw new IllegalArgumentException("No strategy found for gameId: " + gameId);
    }
    return strategy;
  }
}
