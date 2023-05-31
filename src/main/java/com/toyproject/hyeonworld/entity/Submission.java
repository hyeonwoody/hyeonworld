package com.toyproject.hyeonworld.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="submission")
public class Submission {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "submission_id", unique = true)
    private Long id;

    @Column(name = "game")
    private Integer game;

    @Column(name = "number")
    private Integer number;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn (name="member_id")
    private Member member;

    public void setMember (Member member){
        this.member = member;
        member.getSubmissionList().add(this);
    }
}