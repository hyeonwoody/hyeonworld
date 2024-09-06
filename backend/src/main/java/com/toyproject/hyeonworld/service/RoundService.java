package com.toyproject.hyeonworld.service;

import com.toyproject.hyeonworld.entity.Game;
import com.toyproject.hyeonworld.entity.Member;
import com.toyproject.hyeonworld.entity.Round;
import com.toyproject.hyeonworld.entity.Submission;
import com.toyproject.hyeonworld.repository.game.GameRepository;
import com.toyproject.hyeonworld.repository.game.JdbcTemplateGameRepository;
import com.toyproject.hyeonworld.repository.member.JdbcTemplateMemberRepository;
import com.toyproject.hyeonworld.repository.member.MemberRepository;
import com.toyproject.hyeonworld.repository.round.JdbcTemplateRoundRepository;
import com.toyproject.hyeonworld.repository.round.RoundRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


public class RoundService {

    private final JdbcTemplateRoundRepository jdbcTemplateRoundRepository;

    private final JdbcTemplateMemberRepository jdbcTemplateMemberRepository;

    private static List<String> correctMemberNames = new ArrayList<>();

    private static boolean game0Init = false;
    private static int currentRound = 0;

    public RoundService(JdbcTemplateRoundRepository jdbcTemplateRoundRepository,
                        JdbcTemplateMemberRepository jdbcTemplateMemberRepository) {
        this.jdbcTemplateRoundRepository = jdbcTemplateRoundRepository;
        this.jdbcTemplateMemberRepository = jdbcTemplateMemberRepository;
    }

    public Map<String, Object> get0List() {
        Map <String, Object> ret = new HashMap <>();

        if (!game0Init){
            game0Init = true;
            System.out.println("Result STAGE LIST INIT :");
            Round round = jdbcTemplateRoundRepository.getCurrentRound();


            if (round != null) {
                int answer = round.getAnswer();
                correctMemberNames = jdbcTemplateMemberRepository.getCorrectMemberNames(answer);

            }
        }
        ret.put("CorerectNameList", correctMemberNames);
        System.out.println("CORRECT SIZE "+correctMemberNames.size());

        return ret;
    }

    public void postRound0(String memberName) {
       Submission submission = jdbcTemplateMemberRepository.findByNameGetSubmission(memberName);
       jdbcTemplateRoundRepository.updateCurrentRoundAnswer(0L, currentRound, submission);
    }

    public void createNewRound() {
        if (game0Init){
            correctMemberNames.clear();
            game0Init = false;
            ++currentRound;
        }
    }


}
