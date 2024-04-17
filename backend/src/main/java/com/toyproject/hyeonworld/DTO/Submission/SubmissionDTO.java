package com.toyproject.hyeonworld.DTO.Submission;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

@Getter
public class SubmissionDTO extends SubmissionEssential{


    private final Long memberId;

    private final Integer game;


    @JsonCreator
    public SubmissionDTO(@JsonProperty("memberId") Long memberId,
                         @JsonProperty("input") String text,
                         @JsonProperty("inputFalse") Integer number
                         ) {
        this.game = 0;
        this.memberId = memberId;
        this.setNumber(number);
        this.setText(text);
    }


    public SubmissionDTO(Long memberId, Integer game, Integer number, String text) {
        super();
        this.memberId = memberId;
        this.game = game;
        this.setNumber(number);
        this.setText(text);
    }
}