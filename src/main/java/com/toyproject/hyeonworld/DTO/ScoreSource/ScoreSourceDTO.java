package com.toyproject.hyeonworld.DTO.ScoreSource;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.toyproject.hyeonworld.DTO.Submission.SubmissionEssential;
import com.toyproject.hyeonworld.entity.Submission;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ScoreSourceDTO {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_source_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "score", nullable = false)
    private Long score;

    @Column(name = "correct", nullable = false)
    private boolean correct;

    @Column(name = "source", nullable = false)
    private Submission source;

    public ScoreSourceDTO (Long id){
        this.id = id;
    }
}