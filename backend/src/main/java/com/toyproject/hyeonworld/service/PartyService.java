package com.toyproject.hyeonworld.service;

import com.toyproject.hyeonworld.DTO.PartyInitDTO;
import com.toyproject.hyeonworld.DTO.Submission.SubmissionVO;
import com.toyproject.hyeonworld.entity.Member;
import com.toyproject.hyeonworld.entity.Party;
import com.toyproject.hyeonworld.repository.member.JdbcTemplateMemberRepository;
import com.toyproject.hyeonworld.repository.member.MemberRepository;
import com.toyproject.hyeonworld.repository.party.JdbcTemplatePartyRepository;
import com.toyproject.hyeonworld.repository.party.PartyRepository;
import com.toyproject.hyeonworld.repository.submission.JdbcTemplateSubmissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class PartyService {

    private final JdbcTemplatePartyRepository jdbcTemplatePartyRepository;
    private final JdbcTemplateSubmissionRepository jdbcTemplateSubmissionRepository;

    public PartyService(JdbcTemplatePartyRepository jdbcTemplatePartyRepository,
                        JdbcTemplateSubmissionRepository jdbcTemplateSubmissionRepository) {
        this.jdbcTemplatePartyRepository = jdbcTemplatePartyRepository;
        this.jdbcTemplateSubmissionRepository = jdbcTemplateSubmissionRepository;
    }

    public void init(PartyInitDTO partyInitDTO) {
        jdbcTemplatePartyRepository.init(partyInitDTO);
    }

    public void open(int game) {
        jdbcTemplatePartyRepository.open(game);
    }

    public Integer getCurrentGameQuery() {
        return jdbcTemplatePartyRepository.getCurrentGame();
    }

    public Integer setCurrentGameStage(Integer gameStage) {
        jdbcTemplatePartyRepository.setCurrentGameStage(gameStage);
        return gameStage;
    }

    public Integer getCurrentGameStage(){
        return jdbcTemplatePartyRepository.getCurrentGameStage();
    }

    public void putTarget(String memberName) {
        jdbcTemplatePartyRepository.setTarget(memberName);
    }

    public SubmissionVO getTarget() {
        Member member = jdbcTemplatePartyRepository.getTarget();

        if (member != null){
            String text = jdbcTemplateSubmissionRepository.findTextByMemberId(member.getId());
            String[] splitText = text.split(",");
            SubmissionVO submissionPlayer = new SubmissionVO(member.getName(), List.of(splitText));
            return submissionPlayer;
        }
        return null;
    }
}
