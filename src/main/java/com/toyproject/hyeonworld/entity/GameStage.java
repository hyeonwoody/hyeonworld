package com.toyproject.hyeonworld.entity;

import jakarta.persistence.*;

@Entity
public class GameStage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "gameStage_id", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private Byte index;


}
