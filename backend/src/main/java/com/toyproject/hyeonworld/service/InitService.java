package com.toyproject.hyeonworld.service;

import com.toyproject.hyeonworld.repository.member.JdbcTemplateMemberRepository;
import com.toyproject.hyeonworld.repository.member.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class InitService {
    private final JdbcTemplateMemberRepository jdbcTemplateMemberRepository;


    public InitService(JdbcTemplateMemberRepository jdbcTemplateMemberRepository) {
        this.jdbcTemplateMemberRepository = jdbcTemplateMemberRepository;
    }

    public void member() {
        System.out.println("실행");
    }

}
