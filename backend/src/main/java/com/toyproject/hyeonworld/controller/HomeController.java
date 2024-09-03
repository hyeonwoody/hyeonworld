//package com.toyproject.hyeonworld.controller;
//
//import com.toyproject.hyeonworld.entity.Game;
//import com.toyproject.hyeonworld.entity.Member;
//import com.toyproject.hyeonworld.service.GameService;
//import com.toyproject.hyeonworld.service.HomeService;
//import com.toyproject.hyeonworld.service.MemberService;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/home")
//public class HomeController {
//
//    private final HomeService homeService;
//    private final GameService gameService;
//
//    public HomeController(HomeService homeService, GameService gameService) {
//        this.homeService = homeService;
//        this.gameService = gameService;
//    }
//
//
//    @GetMapping("/games/playable")
//    public ResponseEntity<List<Game>> displayGame (HttpServletRequest request){
//
//        List<Game> gameList = gameService.displayGame();
//
//        return ResponseEntity.ok (gameList);
//    }
//
//
//}
