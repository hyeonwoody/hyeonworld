package com.toyproject.hyeonworld.service;

import com.toyproject.hyeonworld.entity.Game;
import com.toyproject.hyeonworld.entity.Member;
import com.toyproject.hyeonworld.entity.Round;
import com.toyproject.hyeonworld.entity.Submission;
import com.toyproject.hyeonworld.repository.GameRepository;
import com.toyproject.hyeonworld.repository.MemberRepository;
import com.toyproject.hyeonworld.repository.RoundRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoundService {

    private final RoundRepository roundRepository;
    private final MemberRepository memberRepository;
    private final GameRepository gameRepository;

    public RoundService(RoundRepository roundRepository, MemberRepository memberRepository, GameRepository gameRepository) {
        this.roundRepository = roundRepository;
        this.memberRepository = memberRepository;
        this.gameRepository = gameRepository;
    }

    public List<String> get0List(int currentRound) {

        Optional <Round> roundOptional = roundRepository.findAll().stream()
                .filter(round-> round.getRound() == currentRound && round.getGame().getId() == 0L)
                .findFirst();

        if (roundOptional.isPresent()) {
            Round pRound = roundOptional.get();
            int answer = pRound.getAnswer();

            List<String> nameList = memberRepository.findAll().stream()
                    .filter (member -> member.getAnswer() == answer)
                    .map(Member::getName)
                    .collect(Collectors.toList());

            return nameList;
        }
        else{
            return null;
        }

    }

    public Round postRound0(int currentRound, String memberName) {

        Optional<Member> memberOptional = memberRepository.findByName(memberName);
        Member pMember = memberOptional.get();

        List<Submission> submissionList = pMember.getSubmissionList();
        Submission submission = submissionList.get(submissionList.size() - 1);
        Optional<Game> game = gameRepository.findById(0L);

        Optional <Round> roundOptional = roundRepository.findAll().stream()
                .filter(round-> round.getRound() == currentRound && round.getGame().getId() == 0L)
                .findFirst();

        if (roundOptional.isPresent()){
            Round pRound = roundOptional.get();

            pRound.setAnswer(submission.getNumber());

            roundRepository.save(pRound);
            System.out.println("ROUND GOGOG");
            return pRound;

        }
        else {
            Round round = new Round(game.get(), currentRound, submission.getNumber());
            return round;
        }
    }
}
