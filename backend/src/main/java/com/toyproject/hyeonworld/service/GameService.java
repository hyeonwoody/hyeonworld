package com.toyproject.hyeonworld.service;

import com.toyproject.hyeonworld.entity.Game;
import com.toyproject.hyeonworld.repository.game.GameRepository;
import com.toyproject.hyeonworld.repository.game.JdbcTemplateGameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final JdbcTemplateGameRepository jdbcTemplateGameRepository;


    public GameService(JdbcTemplateGameRepository jdbcTemplateGameRepository) {
        this.jdbcTemplateGameRepository = jdbcTemplateGameRepository;
    }

    public List<Game> displayGame() {
        return jdbcTemplateGameRepository.findPlayableGames();
    }
}
