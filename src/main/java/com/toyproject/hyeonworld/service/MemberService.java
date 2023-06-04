package com.toyproject.hyeonworld.service;

import com.toyproject.hyeonworld.DTO.Member.MemberAnswer;
import com.toyproject.hyeonworld.DTO.Member.MemberScore;
import com.toyproject.hyeonworld.entity.Member;
import com.toyproject.hyeonworld.entity.Round;
import com.toyproject.hyeonworld.repository.MemberRepository;
import com.toyproject.hyeonworld.repository.PartyRepository;
import com.toyproject.hyeonworld.repository.RoundRepository;
import com.toyproject.hyeonworld.repository.ScoreSourceRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final PartyRepository partyRepository;
    private final MemberRepository memberRepository;
    private final ScoreSourceRepository scoreSourceRepository;
    private final RoundRepository roundRepository;

    public MemberService(MemberRepository memberRepository, PartyRepository partyRepository, ScoreSourceRepository scoreSourceRepository, RoundRepository roundRepository) {
        this.partyRepository = partyRepository;
        this.memberRepository = memberRepository;
        this.scoreSourceRepository = scoreSourceRepository;
        this.roundRepository = roundRepository;
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
            if (pMember == partyRepository.findFirstByOrderByCreatedAtDesc().get().getTarget())
                pMember.setAnswer(-2);
            pMember.setAnswer(memberAnswer.getAnswer());
            memberRepository.save(pMember);
            return pMember.getId();
        }).orElse(-1L);
    }

    public HashMap<String, List<MemberScore>> getRanking() {
        HashMap<String, List<MemberScore>> ret = new HashMap<>();
        List<MemberScore> tmp = new ArrayList<>();
        List<Member> memberList = memberRepository.findAll().stream()
                .filter(member -> member.isLogin())
                .sorted(Comparator.comparing(Member::getTotalScore).reversed())
                .collect(Collectors.toList());
        for (Member member : memberList){
            MemberScore memberScore = new MemberScore(member.getName(), member.getTotalScore());
            tmp.add(memberScore);
        }
        ret.put ("memberList", tmp);
//        for (Member member : memberList){
//            MemberScore memberScore = new MemberScore();
//
//        }

        return ret;
    }

    public List<Member> putScore(int game, HashMap<String, Long> request) {
        Map<Boolean, List<Member>> memberMap;

        Optional <Round> recentAddedRound = roundRepository.findAll().stream()
                .sorted(Comparator.comparing(Round::getCreatedAt).reversed())
                .findFirst();

        if (recentAddedRound.isPresent()) {
            Round pRound = recentAddedRound.get();

            Integer answer = pRound.getAnswer();

            memberMap = memberRepository.findAll().stream()
                    .filter(member -> member.isLogin())
                    .collect(Collectors.partitioningBy(member-> member.getAnswer() == answer));

            for (Member member : memberMap.get(true)){
                System.out.println("CORRECT"+member.getName());
                member.addScore(request.get("correct"));
            }
            for (Member member : memberMap.get(false)){
                System.out.println("WRONG"+member.getName());
                member.addScore(request.get("wrong"));
            }
            memberRepository.saveAll(memberMap.get(false));
            memberRepository.saveAll(memberMap.get(true));

            return memberMap.get(true);
        } else {
            // Handle the case when no entities are found
        }

        return null;
    }
}