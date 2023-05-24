package com.toyproject.hyeonworld.service;

import com.toyproject.hyeonworld.entity.Member;
import com.toyproject.hyeonworld.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long login (String memberName){

        Optional<Member> member = memberRepository.findByName(memberName);

        if (member.isPresent()){
            Member pMember = member.get();
            if (pMember.getName().equals (memberName)){
                pMember.setLogin(true);
                memberRepository.save(pMember);
            }
            return pMember.getId();
        }
        return null;
    }


    public Long logout (Long logoutId){

        Optional<Member> member = memberRepository.findById(logoutId);

        if (member.isPresent()){
            Member pMember = member.get();
            pMember.setLogin(false);
            memberRepository.save(pMember);
            return pMember.getId();
        }
        return null;
    }



}