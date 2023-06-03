package com.toyproject.hyeonworld;

import com.toyproject.hyeonworld.controller.InitController;
import com.toyproject.hyeonworld.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class HyeonworldApplication {

	public static void main(String[] args) {
		SpringApplication.run(HyeonworldApplication.class, args);
	}
}
