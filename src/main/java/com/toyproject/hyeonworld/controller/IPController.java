package com.toyproject.hyeonworld.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class IPController {

    @PostMapping("/ip")
    public ResponseEntity<String> ip (HttpServletRequest request){
        System.out.println("여기 왔다");
        return ResponseEntity.ok (request.getRemoteAddr());
    }
}
