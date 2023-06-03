package com.toyproject.hyeonworld.controller;

import com.toyproject.hyeonworld.service.InitService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/init")
public class InitController {

    private final InitService initService;

    public InitController(InitService initService) {
        this.initService = initService;
    }


    @PutMapping
    public void member (){
        initService.member();
    }
}
