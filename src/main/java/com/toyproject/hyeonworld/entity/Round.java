package com.toyproject.hyeonworld.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name="round")
public class Round {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "round_id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn (name="game_id")
    private Game game;

    @Column(nullable = false)
    private int round;

    @Column(nullable = false)
    private int answer;

    @Column(name = "created_at")
    private Date createdAt;

    public Round (Game game, int round, int answer){
        this.game = game;
        this.round = round;
        this.answer = answer;
    }

    public Round() {

    }

    @PrePersist
    public void prePersist(){
        createdAt = new Date();
    }
}
