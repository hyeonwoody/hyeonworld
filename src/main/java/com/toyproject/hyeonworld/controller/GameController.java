package com.toyproject.hyeonworld.controller;

import com.toyproject.hyeonworld.entity.Game;
import com.toyproject.hyeonworld.service.GameService;
import com.toyproject.hyeonworld.service.ThreadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/game")
public class GameController {
    private GameService gameService;
    private final ThreadService threadService;

    public GameController (GameService gameService, ThreadService threadService){
        this.gameService = gameService;
        this.threadService = threadService;
    }

    @GetMapping("/playable")
    public ResponseEntity<List<Game>> displayGame (){

        Future<List<Game>> futureResult = threadService.executorService.submit(()->{
            return gameService.displayGame();
        });

        try {
            List<Game> gameList = futureResult.get();
            return ResponseEntity.ok (gameList);
        }catch (InterruptedException | ExecutionException e ){
            return ResponseEntity.status(500).build();
        }

    }
}
