package com.toyproject.hyeonworld.DTO.Submission;

import lombok.Data;


@Data
public class SubmissionEssential {
    private Integer number;
    private String text;

    public SubmissionEssential(){

    }

    public SubmissionEssential (Integer number, String text){
        this.number = number;
        this.text = text;
    }

}
