package com.toyproject.hyeonworld.service;

import com.toyproject.hyeonworld.entity.Game;
import com.toyproject.hyeonworld.entity.Member;
import com.toyproject.hyeonworld.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return -1L;
    }


    public Long logout (Long logoutId){

        Optional<Member> member = memberRepository.findById(logoutId);

        if (member.isPresent()){
            Member pMember = member.get();
            pMember.setLogin(false);
            memberRepository.save(pMember);
            return pMember.getId();
        }
        return -1L;
    }


    public Long enterGame(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if (member.isPresent()){
            System.out.println("enter GAME");
            Member pMember = member.get();
            pMember.setEnterGame(true);
            memberRepository.save(pMember);
            return pMember.getId();
        }
        return -1L;
    }

    public Long exitGame(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if (member.isPresent()){
            System.out.println("exit GAME");
            Member pMember = member.get();
            pMember.setEnterGame(false);
            memberRepository.save(pMember);
            return pMember.getId();
        }
        return -1L;
    }

    public List<String> getWaitingList() {
        return memberRepository.findAll().stream()
                .filter(member -> member.isLogin())
                .filter(member -> !member.getEnterGame())
                .map(Member::getName)
                .collect(Collectors.toList());
    }

}