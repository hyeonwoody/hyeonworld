package com.toyproject.hyeonworld.entity;

import lombok.AllArgsConstructor;

import java.util.Date;


@AllArgsConstructor
public class Party {


    private Long id;
    private Integer partyType;
    private Integer persons;
    private Integer currentGame;
    private Integer currentGameStage;
    private Date createdAt;
    private Member target;

    public void prePersist(){
        createdAt = new Date();
    }

}
