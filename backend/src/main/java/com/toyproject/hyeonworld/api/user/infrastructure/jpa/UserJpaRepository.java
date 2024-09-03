package com.toyproject.hyeonworld.api.user.infrastructure.jpa;

import com.toyproject.hyeonworld.api.game.infrastructure.entity.Game;
import com.toyproject.hyeonworld.api.user.infrastructure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public interface UserJpaRepository extends JpaRepository<User, Long> {

}
