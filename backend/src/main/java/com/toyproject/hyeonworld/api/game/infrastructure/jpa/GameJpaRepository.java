package com.toyproject.hyeonworld.api.game.infrastructure.jpa;

import com.toyproject.hyeonworld.api.game.infrastructure.GameRepository;
import com.toyproject.hyeonworld.api.game.infrastructure.entity.Game;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public interface GameJpaRepository extends JpaRepository<Game, Long> {
}
