package com.toyproject.hyeonworld.entity;

import com.toyproject.hyeonworld.repository.GameRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

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

    @Column(name="current_game_stage", nullable = false)
    private Integer currentGameStage;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member target;

    @Column(name = "created_at")
    private Date createdAt;



    @PrePersist
    public void prePersist(){
        createdAt = new Date();
    }

}
