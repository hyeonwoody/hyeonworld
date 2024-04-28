package com.toyproject.hyeonworld.configuration.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class WebConfig implements WebMvcConfigurer {



    @Value("${my.ipAddress}")
    String ipAddress;

    @Value("${my.frontEndPort}")
    String frontEndPort;

    @Override
    public void addCorsMappings(CorsRegistry registry){

        registry.addMapping("/api/game-stage")
                .allowedMethods("PUT", "GET")
                .allowCredentials(true)
                .allowedOrigins("http://"+this.ipAddress+":"+this.frontEndPort);

        registry.addMapping("/api/game-stage/init")
                .allowedMethods("GET")
                .allowCredentials(true)
                .allowedOrigins("http://"+this.ipAddress+":"+this.frontEndPort);

        registry.addMapping("/member/init")
                .allowedMethods("PUT")
                .allowCredentials(true)
                .allowedOrigins("http://"+this.ipAddress+":"+this.frontEndPort);

        registry.addMapping("/member/login-confirm")
                .allowedMethods("POST")
                .allowedHeaders("*")
                .allowCredentials(true)
                .allowedOrigins("http://"+this.ipAddress+":"+this.frontEndPort);

        registry.addMapping("/member/logout-confirm")
                .allowedMethods("POST")
                .allowedHeaders("*")
                .allowCredentials(true)
                .allowedOrigins("http://"+this.ipAddress+":"+this.frontEndPort);

        registry.addMapping("/member/enter-game")
                .allowedMethods("POST")
                .allowCredentials(true)
                .allowedOrigins("http://"+this.ipAddress+":"+this.frontEndPort);

        registry.addMapping("/member/exit-game")
                .allowedMethods("POST")
                .allowCredentials(true)
                .allowedOrigins("http://"+this.ipAddress+":"+this.frontEndPort);

        registry.addMapping("/member/waiting-list/init")
                .allowedMethods("GET")
                .allowCredentials(true)
                .allowedOrigins("http://"+this.ipAddress+":"+this.frontEndPort);

        registry.addMapping("/member/waiting-list/additional")
                .allowedMethods("GET")
                .allowCredentials(true)
                .allowedOrigins("http://"+this.ipAddress+":"+this.frontEndPort);

        registry.addMapping("/member/play/0")
                .allowedMethods("PUT")
                .allowCredentials(true)
                .allowedOrigins("http://"+this.ipAddress+":"+this.frontEndPort);

        registry.addMapping("/member/score/0")
                .allowedMethods("PUT")
                .allowCredentials(true)
                .allowedOrigins("http://"+this.ipAddress+":"+this.frontEndPort);

        registry.addMapping("/member/ranking")
                .allowedMethods("GET")
                .allowCredentials(true)
                .allowedOrigins("http://"+this.ipAddress+":"+this.frontEndPort);

        registry.addMapping("/game/playable")
                .allowCredentials(true)
                .allowedOrigins("http://"+this.ipAddress+":"+this.frontEndPort);

        registry.addMapping("/party/current-game")
                .allowedMethods("PUT", "GET")
                .allowCredentials(true)
                .allowedOriginPatterns("http://"+this.ipAddress+":"+this.frontEndPort);

        registry.addMapping("/party/target")
                .allowedMethods("PUT", "GET")
                .allowCredentials(true)
                .allowedOriginPatterns("http://"+this.ipAddress+":"+this.frontEndPort);

        registry.addMapping("/party/init")
                .allowedMethods("POST")
                .allowCredentials(true)
                .allowedOrigins("http://"+this.ipAddress+":"+this.frontEndPort);

        registry.addMapping("/submission/0")
                .allowedMethods("POST", "GET")
                .allowCredentials(true)
                .allowedOrigins("http://"+this.ipAddress+":"+this.frontEndPort);

        registry.addMapping("/round/0")
                .allowedMethods("POST", "GET", "PUT")
                .allowCredentials(true)
                .allowedOrigins("http://"+this.ipAddress+":"+this.frontEndPort);
    }
}
