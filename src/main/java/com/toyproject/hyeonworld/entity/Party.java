package com.toyproject.hyeonworld.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity
@Data
@Table (name="party")
public class Party {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_id", unique = true)
    private Long id;

    @Column(name="party_type", nullable = false)
    private Integer partyType;

    @Column(name="persons", nullable = false)
    private Integer persons;

    @Column(name="current_game", nullable = false)
    private Integer currentGame;

}