package com.toyproject.hyeonworld.entity;

import com.toyproject.hyeonworld.service.SubmissionService;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table (name="member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private Boolean player;

    @Column(length = 50)
    private String email;

    @Column(nullable = false)
    private Boolean login;

    @Column(nullable = false)
    private Byte partyType; //0 : 식구, 1 : 친가, 2 : 외가

    @Column(length = 15, nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean proposition; //0 : 은 이, 1 : 는 가

    @Column(nullable = false)
    private Byte relation;

    @Column(name="nick_name", nullable = false)
    private String nickName;

    @Column(name="nick_name_proposition", nullable = false)
    private Boolean nickNameProposition;

    @Column(name="in_game", nullable = false)
    private Boolean inGame;

    @Column(name="total_score", nullable = false)
    private long totalScore;

    @OneToMany (mappedBy = "member")
    private List<ScoreSource> scoreSourceList = new ArrayList<>();

    @Column(name="answer", nullable = false, columnDefinition = "-1")
    private int answer;

    @OneToMany (mappedBy = "member")
    private List<Submission> submissionList = new ArrayList<>();



    public Member(){
        this.login = false;
    }

    public boolean isLogin() {
        return this.login;
    }
    public boolean isPlayer() {return this.player;}
    public boolean isInGame() {
        return this.inGame;
    }
    public Submission getSubmission () {
        return submissionList.get(submissionList.size() - 1);
    }
    public Long addScore (Long scoreToAdd){
        this.totalScore += scoreToAdd;
        return this.totalScore;
    }
}
