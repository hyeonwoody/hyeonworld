package com.toyproject.hyeonworld.service;

import com.toyproject.hyeonworld.entity.Game;
import com.toyproject.hyeonworld.entity.Member;
import com.toyproject.hyeonworld.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository gameRepository;


    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> displayGame() {
        return gameRepository.findAll().stream()
                .filter(game -> game.isPlayable())
                .collect(Collectors.toList());
    }
}
