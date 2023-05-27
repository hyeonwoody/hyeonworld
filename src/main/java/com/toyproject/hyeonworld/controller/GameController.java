package com.toyproject.hyeonworld.controller;

import com.toyproject.hyeonworld.entity.Game;
import com.toyproject.hyeonworld.repository.GameRepository;
import com.toyproject.hyeonworld.service.GameService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {
    private GameService gameService;

    public GameController (GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping("/playable")
    public ResponseEntity<List<Game>> displayGame (){

        List<Game> gameList = gameService.displayGame();

        return ResponseEntity.ok (gameList);
    }
}
