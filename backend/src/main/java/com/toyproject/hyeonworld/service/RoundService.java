package com.toyproject.hyeonworld.service;

import com.toyproject.hyeonworld.entity.Game;
import com.toyproject.hyeonworld.entity.Member;
import com.toyproject.hyeonworld.entity.Round;
import com.toyproject.hyeonworld.entity.Submission;
import com.toyproject.hyeonworld.repository.GameRepository;
import com.toyproject.hyeonworld.repository.MemberRepository;
import com.toyproject.hyeonworld.repository.RoundRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoundService {

    private final RoundRepository roundRepository;
    private final MemberRepository memberRepository;
    private final GameRepository gameRepository;

    private static List<String> game0CorrectList = new ArrayList<>();

    private static boolean game0Init = false;
    private static int currentRound = 0;

    public RoundService(RoundRepository roundRepository, MemberRepository memberRepository, GameRepository gameRepository) {
        this.roundRepository = roundRepository;
        this.memberRepository = memberRepository;
        this.gameRepository = gameRepository;
    }

    public Map<String, Object> get0List() {
        Map <String, Object> ret = new HashMap <>();

        if (!game0Init){
            game0Init = true;
            System.out.println("Result STAGE LIST INIT :");
            Optional <Round> roundOptional = roundRepository.findAll().stream()
                    .max(Comparator.comparing(Round::getCreatedAt));


            if (roundOptional.isPresent()) {
                Round pRound = roundOptional.get();
                int answer = pRound.getAnswer();

                this.game0CorrectList = memberRepository.findAll().stream()
                        .filter (member -> member.getAnswer() == answer)
                        .map(Member::getName)
                        .collect(Collectors.toList());
            }
        }
        ret.put("CorerectNameList", game0CorrectList);
        System.out.println("CORRECT SIZE "+game0CorrectList.size());

        return ret;
    }

    public Round postRound0(String memberName) {

        Optional<Member> memberOptional = memberRepository.findByName(memberName);
        Member pMember = memberOptional.get();

        List<Submission> submissionList = pMember.getSubmissionList();
        Submission submission = submissionList.get(submissionList.size() - 1);
        Optional<Game> game = gameRepository.findById(0L);

        Optional <Round> roundOptional = roundRepository.findAll().stream()
                .filter(round -> round.getGame().getId() == 0L)
                .filter(round -> round.getRound() == currentRound)
                .findFirst();

        System.out.println("LIST SIZE "+ roundOptional.stream().toList().size());

        if (roundOptional.isPresent()){
            Round pRound = roundOptional.get();

            pRound.setAnswer(submission.getNumber());
            pRound.setCreatedAt(new Date());

            roundRepository.save(pRound);
            System.out.println("ROUND GOGOG");
            return pRound;

        }
        else {
            Round round = new Round(game.get(), currentRound, submission.getNumber());
            roundRepository.save(round);
            return round;
        }
    }

    public void createNewRound() {
        if (game0Init){
            game0CorrectList.clear();
            game0Init = false;
            ++currentRound;
        }
    }


}
