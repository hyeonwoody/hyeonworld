package com.toyproject.hyeonworld.api.game.domain;

import com.toyproject.hyeonworld.api.game.domain.dto.out.GameInfos;
import com.toyproject.hyeonworld.api.game.infrastructure.GameRepository;
import com.toyproject.hyeonworld.api.game.strategy.GameStrategy;
import com.toyproject.hyeonworld.api.game.strategy.GameFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
@Service
@RequiredArgsConstructor
public class GameService {
    private final GameFactory gameFactory;
    private final GameRepository gameRepository;

  public GameInfos displayGame() {
    return GameInfos.from(gameRepository.findByPlayable(true));
  }

    @Cacheable(cacheNames = "gameStrategy", key = "#gameId")
    public GameStrategy getGame(long gameId) {
        return gameFactory.getStrategy(gameId);
    }
}
