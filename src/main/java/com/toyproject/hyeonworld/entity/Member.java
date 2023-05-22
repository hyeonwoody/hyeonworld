package com.toyproject.hyeonworld.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Table (name="member")
public class Member {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", unique = true, nullable = false)
    private Long id;

    @Column(length = 50)
    private String email;

    @Column(length = 15, nullable = false)
    private String name;

    @Column(nullable = false)
    private Byte familySide; //0 : 식구, 1 : 친가, 2 : 외가

    @Column(nullable = false)
    private Boolean proposition; //0 : 은 이, 1 : 는 가

    @Column(nullable = false)
    private Boolean login;

    private long score;

    public Member(){
        this.login = false;
    }
}
