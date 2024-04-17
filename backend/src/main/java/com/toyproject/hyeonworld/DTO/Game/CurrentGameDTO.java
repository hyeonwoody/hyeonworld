package com.toyproject.hyeonworld.DTO.Game;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrentGameDTO {
    private Integer id;

    public Integer getId() {
        return this.id;
    }

    @JsonCreator
    public CurrentGameDTO(@JsonProperty("game") Integer game
    ) {
        this.id = game;
    }
}