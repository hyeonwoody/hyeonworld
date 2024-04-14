package com.toyproject.hyeonworld.entity;

import com.toyproject.hyeonworld.DTO.ScoreSource.ScoreSourceDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="score_source")
public class ScoreSource extends ScoreSourceDTO {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_source_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "sa", nullable = false)
    private Long sa;

    @ManyToOne
    @JoinColumn (name="member_id")
    private Member member;

    public ScoreSource (Long id){
        super(id);
        this.id = id;
    }

    public ScoreSource() {
        super(null);
    }
}
