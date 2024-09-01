package com.toyproject.hyeonworld.entity;


import lombok.Data;


@Data
public class Game {
    private Long id;
    private String name;
    private String description;
    private boolean playable;

    public Game(long gameId, String name, String description) {
        this.id = gameId;
        this.name = name;
        this.description = description;
    }
}
