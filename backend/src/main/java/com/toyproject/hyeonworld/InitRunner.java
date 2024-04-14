package com.toyproject.hyeonworld;

import com.toyproject.hyeonworld.service.InitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitRunner implements CommandLineRunner {
    private final InitService initService;


    public InitRunner(InitService initService) {
        this.initService = initService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Command Line Runner 실행");
    }
}
