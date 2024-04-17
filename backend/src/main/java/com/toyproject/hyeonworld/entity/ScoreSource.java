package com.toyproject.hyeonworld.entity;

import com.toyproject.hyeonworld.DTO.ScoreSource.ScoreSourceDTO;



public class ScoreSource extends ScoreSourceDTO {

    private Long id;


    private Long sa;


    private Member member;

    public ScoreSource (Long id){
        super(id);
        this.id = id;
    }

    public ScoreSource() {
        super(null);
    }
}
