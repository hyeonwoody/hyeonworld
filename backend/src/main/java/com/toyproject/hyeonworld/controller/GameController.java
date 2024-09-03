//package com.toyproject.hyeonworld.controller;
//
//import com.toyproject.hyeonworld.entity.Game;
//import com.toyproject.hyeonworld.service.GameService;
//import com.toyproject.hyeonworld.service.ThreadService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.Future;
//
//@RestController
//@RequestMapping("/game")
//public class GameController {
//    private GameService gameService;
//
//    public GameController (GameService gameService){
//        this.gameService = gameService;
//    }
//
//    @GetMapping("/playable")
//    public ResponseEntity<List<Game>> displayGame (){
//        List<Game> gameList = gameService.displayGame();
//        return ResponseEntity.ok (gameList);
//    }
//}
