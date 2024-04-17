package com.toyproject.hyeonworld.entity;

import lombok.Data;

import java.util.Date;


@Data
public class Submission {

    private Long id;

    private Integer game;

    private Integer number;


    private String text;


    private Member member;

    private Date createdAt;

    public Submission(String text){
        this.text = text;
    }
    public Submission(Integer number) {

        this.number = number;

    }


    public Submission(Integer game, Integer number, String text) {
        this.game = game;
        this.number = number;
        this.text = text;
    }


    public void prePersist(){
        createdAt = new Date();
    }

    public void setMember (Member member){
        this.member = member;
        member.getSubmissionList().add(this);
    }

    public void setAll (Submission submission){
        this.id = submission.id;
        this.game = submission.game;
        this.number = submission.number;
        this.text = submission.text;
    }
}