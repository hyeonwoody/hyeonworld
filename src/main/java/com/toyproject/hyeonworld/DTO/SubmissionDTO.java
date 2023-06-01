package com.toyproject.hyeonworld.DTO;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubmissionDTO {


    private Long memberId;

    private Integer game;

    private Integer number;

    private String text;
    private List<String> textList;

    @JsonCreator
    public SubmissionDTO(@JsonProperty("memberId") Long memberId,
                         @JsonProperty("input") String text,
                         @JsonProperty("inputFalse") Integer number
                         ) {
        this.game = 0;
        this.memberId = memberId;
        this.number = number;
        this.textList = List.of(text.split(","));
        this.text = text.replace(',',';');
    }


    public SubmissionDTO(Long memberId, Integer game, Integer number, String text) {
        this.memberId = memberId;
        this.game = game;
        this.number = number;
        this.text = text;
        this.textList = List.of(text.split(";"));
    }

    public SubmissionDTO(Long memberId, Integer game, Integer number, List<String> textList) {
        this.memberId = memberId;
        this.game = game;
        this.number = number;
        this.text = String.join(";", textList);
        this.textList = textList;
    }
}