package com.toyproject.hyeonworld.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Table (name="member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", unique = true, nullable = false)
    private Long id;

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

    @Column(name="nick_name", nullable = false)
    private String nickName;

    @Column(name="nick_name_proposition", nullable = false)
    private Boolean nickNameProposition;

    @Column(name="in_game", nullable = false)
    private Boolean inGame;

    private long score;

    public Member(){
        this.login = false;
    }

    public boolean isLogin() {
        return this.login;
    }
    public boolean isInGame() {
        return this.inGame;
    }
}
