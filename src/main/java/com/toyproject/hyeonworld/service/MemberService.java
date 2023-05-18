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

    public Member login (String memberName){

        Optional<Member> member = memberRepository.findByName(memberName);

        if (member.isPresent()){
            Member pMember = member.get();
            if (pMember.getName().equals (memberName)){
                pMember.setLogin(true);
                memberRepository.save(pMember);
            }
            return pMember;
        }
        return null;
    }


    public Member logout (String memberName){

        Optional<Member> member = memberRepository.findByName(memberName);

        if (member.isPresent()){
            Member pMember = member.get();
            if (pMember.getName().equals (memberName)){
                pMember.setLogin(false);
                memberRepository.save(pMember);
            }
            return pMember;
        }
        return null;
    }

}
