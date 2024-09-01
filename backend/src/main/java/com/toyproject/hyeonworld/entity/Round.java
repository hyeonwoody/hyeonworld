package com.toyproject.hyeonworld.entity;


import lombok.Data;
import lombok.Getter;

import java.util.Date;


@Getter
public class Round {


    private Long id;
    private int answer;
    private int round;
    private Game game;
    private Date createdAt;

    public Round() {

    }

    public Round(int answer) {
        this.answer = answer;
    }

}
