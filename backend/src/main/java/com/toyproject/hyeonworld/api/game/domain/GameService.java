package com.toyproject.hyeonworld.api.game.domain;

import com.toyproject.hyeonworld.api.game.domain.dto.out.GameInfo;
import com.toyproject.hyeonworld.api.game.domain.dto.out.GameInfos;
import com.toyproject.hyeonworld.api.game.infrastructure.GameRepository;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
@Service
@RequiredArgsConstructor
public class GameService {
  private final GameRepository gameRepository;

  public GameInfos displayGame() {
    return GameInfos.from(gameRepository.findByPlayable(true));
  }
}
