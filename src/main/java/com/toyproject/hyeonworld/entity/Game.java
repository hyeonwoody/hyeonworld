package com.toyproject.hyeonworld.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Table (name="game")
public class Game {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id", unique = true, nullable = false)
    private Long id;

    @Column(length = 20)
    private String name;

    @Column(length = 70, nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean playable;

}
