package com.toyproject.hyeonworld.entity;


import lombok.Data;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
public class Member {

    private Long id;


    private Boolean player;


    private String email;


    private Boolean login;

    private Byte partyType; //0 : 식구, 1 : 친가, 2 : 외가


    private String name;


    private boolean proposition; //0 : 은 이, 1 : 는 가


    private Byte relation;


    private String nickName;


    private Boolean nickNameProposition;


    private Boolean inGame;


    private long totalScore;


    private List<ScoreSource> scoreSourceList = new ArrayList<>();


    private int answer;


    private List<Submission> submissionList = new ArrayList<>();

    public Member (Long id, String name, boolean login, boolean player){ //
        this.id = id;
        this.name = name;
        this.login = login;
        this.player = player;
    }
    public Member(long id, boolean login) {
        this.id = id;
        this.login = login;
    }
    public Member (Long id){
        this.id = id;
    }

    public Member(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Member (String name){
        this.name = name;
    }

    public Member(String name, List<Submission> submissionList){
        this.name = name;
        this.submissionList.addAll(submissionList);
    }

    public Member(){
        this.login = false;
    }

    public Member(String name, long totalScore) {
        this.name = name;
        this.totalScore = totalScore;
    }

    public Member(long id, boolean login, boolean inGame) {
        this.id = id;
        this.login = login;
        this.inGame = inGame;
    }

    public Member(long id, boolean login, boolean inGame, boolean player) {
        this.id = id;
        this.login = login;
        this.inGame = inGame;
        this.player = player;
    }


    public boolean isLogin() {
        return this.login;
    }
    public boolean isPlayer() {return this.player;}
    public Submission getSubmission () {
        return submissionList.get(submissionList.size() - 1);
    }
    public Long addScore (Long scoreToAdd){
        this.totalScore += scoreToAdd;
        return this.totalScore;
    }
}
