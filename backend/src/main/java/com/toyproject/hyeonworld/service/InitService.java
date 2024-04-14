package com.toyproject.hyeonworld.service;

import com.toyproject.hyeonworld.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class InitService {
    private final MemberRepository memberRepository;


    public InitService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void member() {
        System.out.println("실행");
        memberRepository.updateLoginAndInGameColumns();
    }

}
