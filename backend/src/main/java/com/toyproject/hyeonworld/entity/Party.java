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


    private Member target;

    private Date createdAt;




    public void prePersist(){
        createdAt = new Date();
    }

}
