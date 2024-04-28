package com.toyproject.hyeonworld.service;

import com.toyproject.hyeonworld.DTO.Member.MemberAnswer;
import com.toyproject.hyeonworld.DTO.Member.MemberCreateDTO;
import com.toyproject.hyeonworld.DTO.Member.MemberDTO;
import com.toyproject.hyeonworld.DTO.Member.MemberScore;
import com.toyproject.hyeonworld.entity.Member;
import com.toyproject.hyeonworld.entity.Round;
import com.toyproject.hyeonworld.repository.member.MemberRepository;
import com.toyproject.hyeonworld.repository.member.JdbcTemplateMemberRepository;
import com.toyproject.hyeonworld.repository.party.JdbcTemplatePartyRepository;
import com.toyproject.hyeonworld.repository.party.PartyRepository;
import com.toyproject.hyeonworld.repository.round.RoundRepository;
import com.toyproject.hyeonworld.repository.round.JdbcTemplateRoundRepository;
import com.toyproject.hyeonworld.repository.ScoreSourceRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final JdbcTemplatePartyRepository jdbcTemplatePartyRepository;
    private final JdbcTemplateMemberRepository jdbcTemplateMemberRepository;
    private final JdbcTemplateRoundRepository jdbcTemplateRoundRepository;

    public MemberService(JdbcTemplatePartyRepository jdbcTemplatePartyRepository,
                        JdbcTemplateMemberRepository jdbcTemplateMemberRepository,
                         JdbcTemplateRoundRepository jdbcTemplateRoundRepository) {
        this.jdbcTemplatePartyRepository = jdbcTemplatePartyRepository;
        this.jdbcTemplateMemberRepository = jdbcTemplateMemberRepository;
        this.jdbcTemplateRoundRepository = jdbcTemplateRoundRepository;
    }


    public Long create(MemberCreateDTO member) {
        Member pMember = jdbcTemplateMemberRepository.findByName(member.getName());
        if (pMember == null){
            return jdbcTemplateMemberRepository.create(member);
        }
        else {
            return -1L;
        }
    }

    public MemberDTO findById(Long id) {
        return jdbcTemplateMemberRepository.findDTOById(id);
    }

    public Long edit(MemberCreateDTO member) {
        return jdbcTemplateMemberRepository.edit(member);
    }

    public void delete(Long id) {
    }

    public Long init() {
        jdbcTemplateMemberRepository.init();
        return -1L;
    }
    public Long login (String memberName){
        Member member = jdbcTemplateMemberRepository.findByName(memberName);
        if (member != null){
            if (member.isLogin()) {
                return -2L;
            }
            if (member.isPlayer())
                jdbcTemplateMemberRepository.toggleLogin(member);
            return member.getId();
        }
        return -1L;
    }


    public Long logout (Long logoutId){

        Member member = jdbcTemplateMemberRepository.findById(logoutId);


        if (member != null){
            if (member.isPlayer())
                jdbcTemplateMemberRepository.toggleLogin(member);
            return member.getId();
        }
        return -1L;
    }

    public String enterGame(Long memberId) {

        Member member = jdbcTemplateMemberRepository.findById(memberId);

        if (member != null){
            jdbcTemplateMemberRepository.toggleinGame(member);
            return member.getName();
        }

        return null;
    }

    public String exitGame (Long memberId) {
        Member member = jdbcTemplateMemberRepository.findById(memberId);

        if (member != null){
            jdbcTemplateMemberRepository.toggleinGame(member);
            return member.getName();
        }

        return null;
    }

    public List<String> getWaitingList() {
        List<Member> members = jdbcTemplateMemberRepository.findLoggedInButNotInGameMembers();

        return members.stream()
                .map(Member::getName)
                .collect(Collectors.toList());
    }


    public Long putPlayGame0(MemberAnswer memberAnswer) {
        Member member = jdbcTemplateMemberRepository.findById(memberAnswer.getMemberId());
        if (member != null){
            if (member.getId() == jdbcTemplatePartyRepository.getTarget().getId()){
                member.setAnswer(-2);
            }
            else {
                member.setAnswer(memberAnswer.getAnswer());
            }
            jdbcTemplateMemberRepository.setAnswer(member);
            return member.getId();
        }
        return -1L;
    }

    public HashMap<String, List<MemberScore>> getRanking() {
        HashMap<String, List<MemberScore>> ret = new HashMap<>();
        List<MemberScore> tmp = new ArrayList<>();

        List<Member> members = jdbcTemplateMemberRepository.getTopScoringParticipants();

        for (Member member : members){
            MemberScore memberScore = new MemberScore(member.getName(), member.getTotalScore());
            tmp.add(memberScore);
        }
        ret.put ("memberList", tmp);

        return ret;
    }

    public List<Member> putScore(HashMap<String, Long> request) {
        Round round = jdbcTemplateRoundRepository.getCurrentRound();

        if (round != null) {

            Integer answer = round.getAnswer();

            List <Member> correctMembers = jdbcTemplateMemberRepository.getCorrectMembers(answer);
            jdbcTemplateMemberRepository.updateScore(correctMembers, request.get("correct"), request.get("wrong"));


            return correctMembers;
        } else {
            // Handle the case when no entities are found
        }

        return null;
    }



}