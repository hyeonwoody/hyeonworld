package com.toyproject.hyeonworld.service;

import com.toyproject.hyeonworld.DTO.PartyInitDTO;
import com.toyproject.hyeonworld.DTO.Submission.SubmissionVO;
import com.toyproject.hyeonworld.entity.Member;
import com.toyproject.hyeonworld.entity.Party;
import com.toyproject.hyeonworld.repository.MemberRepository;
import com.toyproject.hyeonworld.repository.PartyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartyService {

    private final PartyRepository partyRepository;
    private final MemberRepository memberRepository;

    public PartyService(PartyRepository partyRepository, MemberRepository memberRepository) {
        this.partyRepository = partyRepository;
        this.memberRepository = memberRepository;
    }

    public void init(PartyInitDTO partyInitDTO) {
        Party party = new Party();

        party.setPartyType(partyInitDTO.getPartyType());
        party.setPersons(partyInitDTO.getPersons());
        party.setCurrentGame(-1);
        party.setCurrentGameStage(0);
        partyRepository.save(party);
    }

    public void init(Party party) {
        partyRepository.save(party);
    }

    public void open(Integer game) {

        Optional<Party> party = partyRepository.findRecentOne();
        if (party.isPresent()){
            Party pParty = party.get();
            pParty.setCurrentGame(game);
            partyRepository.save(pParty);
        }
    }

    public Integer getCurrentGameQuery() {
        return partyRepository.getCurrentGame();
    }

    public Integer setCurrentGameStage(Integer gameStage) {
        Optional<Party> party = partyRepository.findRecentOne();
        if (party.isPresent()){
            Party pParty = party.get();
            pParty.setCurrentGameStage(gameStage);
            partyRepository.save(pParty);
            return gameStage;
        }
        return -1;
    }

    public Integer getCurrentGameStage(){
        Optional <Party> recentParty = partyRepository.findFirstByOrderByCreatedAtDesc();
        System.out.println("STAGE : "+ recentParty.get().getCurrentGameStage());
        return recentParty.get().getCurrentGameStage();
    }

    public Integer getCurrentGameStageQuery() {
        return partyRepository.getCurrentGameStage();
    }

    public Integer getCurrentPartyType (){
        return partyRepository.getCurrentPartyType();
    }


    public void putTarget(String memberName) {
        Optional<Party> party = partyRepository.findRecentOne();
        if (party.isPresent()){
            Party pParty = party.get();
            Optional<Member> member = memberRepository.findByName(memberName);
            Member foundMember = member.orElseThrow(() -> new IllegalArgumentException("Member not found"+memberName));
            pParty.setTarget(foundMember);
            partyRepository.save(pParty);
        }
    }

    public SubmissionVO getTarget() {
        Optional<Party> party = partyRepository.findRecentOne();

        if (party.isPresent()){
            Party pParty = party.get();
            Member member = pParty.getTarget();
            String tmp[] = member.getSubmission().getText().split(",");
            SubmissionVO submissionPlayer = new SubmissionVO(member.getName(), List.of(tmp));
            return submissionPlayer;
        }
        return null;
    }
}
