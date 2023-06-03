package com.toyproject.hyeonworld.service;

import com.toyproject.hyeonworld.DTO.Member.MemberAnswer;
import com.toyproject.hyeonworld.DTO.Member.MemberScore;
import com.toyproject.hyeonworld.entity.Member;
import com.toyproject.hyeonworld.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public Long init() {
        List<Member> members = memberRepository.findAll().stream()
                .collect(Collectors.toList());

        members.forEach(member -> {
            member.setLogin(false);
            member.setInGame(false);
        });
        memberRepository.saveAll(members);

        return -1L;
    }
    public Long login (String memberName){

        Optional<Member> member = memberRepository.findByName(memberName);

        if (member.isPresent()){
            Member pMember = member.get();
            if (pMember.getLogin().equals (false)){
                pMember.setLogin(true);
                memberRepository.save(pMember);
                return pMember.getId();
            }
            return -2L;
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

    public String enterGame_String(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        return member.map (pMember -> {
            pMember.setInGame(true);
            memberRepository.save(pMember);
            return pMember.getName();
        }).orElse("");
    }

    public Long enterGame_Long(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        return member.map (pMember -> {
            pMember.setInGame(true);
            memberRepository.save(pMember);
            return pMember.getId();
        }).orElse(-1L);
    }

    public String exitGame_String (Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);

        return member.map (pMember -> {
            pMember.setInGame(false);
            memberRepository.save(pMember);
            return pMember.getName();
        }).orElse("");
    }

    public Long exitGame_Long (Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);

        return member.map (pMember -> {
            pMember.setInGame(false);
            memberRepository.save(pMember);
            return pMember.getId();
        }).orElse(-1L);
    }

    public List<String> getAllList() {
        return memberRepository.findAll().stream()
                .map(Member::getName)
                .collect(Collectors.toList());
    }

    public List<String> getWaitingListWithPartyType(Integer partyType) {
        return memberRepository.findAll().stream()
                .filter(member -> member.getPartyType().intValue() == partyType || member.getPartyType().intValue() == 0)
                .filter(member -> member.isLogin())
                .filter(member -> !member.isInGame())
                .map(Member::getName)
                .collect(Collectors.toList());
    }

    public List<String> getWaitingList() {
        return memberRepository.findAll().stream()
                .filter(member -> member.isLogin())
                .filter(member -> !member.isInGame())
                .map(Member::getName)
                .collect(Collectors.toList());
    }


    public Long putPlayGame0(MemberAnswer memberAnswer) {
        Optional<Member> member = memberRepository.findById(memberAnswer.getMemberId());

        return member.map (pMember -> {
            pMember.setAnswer(memberAnswer.getAnswer());
            memberRepository.save(pMember);
            return pMember.getId();
        }).orElse(-1L);
    }

    public List<MemberScore> getRanking() {
        List<Member> memberList = memberRepository.findAll().stream()
                .filter(member -> member.isLogin())
                .sorted(Comparator.comparing(Member::getScore))
                .collect(Collectors.toList());

        List<MemberScore> ret = new ArrayList<>();

//        for (Member member : memberList){
//            MemberScore memberScore = new MemberScore();
//
//        }

        return ret;
    }
}