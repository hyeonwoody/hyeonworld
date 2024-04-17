package com.toyproject.hyeonworld.entity;


import lombok.Data;
import lombok.Getter;

import java.util.Date;


@Getter
public class Round {


    private Long id;


    private Game game;


    private int round;


    private int answer;


    private Date createdAt;



    public Round() {

    }

    public Round(int answer) {
        this.answer = answer;
    }

}
