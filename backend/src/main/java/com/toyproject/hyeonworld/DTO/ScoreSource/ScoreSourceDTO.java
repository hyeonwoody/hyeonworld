package com.toyproject.hyeonworld.DTO.ScoreSource;

import com.toyproject.hyeonworld.entity.Submission;

import lombok.Data;

@Data
public class ScoreSourceDTO {


    private Long id;

    private Long score;


    private boolean correct;


    private Submission source;

    public ScoreSourceDTO (Long id){
        this.id = id;
    }
}