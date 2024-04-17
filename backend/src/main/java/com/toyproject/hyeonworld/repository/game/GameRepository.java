package com.toyproject.hyeonworld.repository.game;

import com.toyproject.hyeonworld.entity.Game;

import org.springframework.stereotype.Repository;


import java.util.Optional;


@Repository
public interface GameRepository  {
    Optional<Game> findByName (String name);


}
