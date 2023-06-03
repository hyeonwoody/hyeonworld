package com.toyproject.hyeonworld.DTO.Submission;

import lombok.Data;

import java.util.List;

@Data
public class SubmissionVO {

    private final String name;
    private final List<String> textList;

    public SubmissionVO(String name, List<String> text) {
        this.name = name;
        this.textList = text;
    }
}
