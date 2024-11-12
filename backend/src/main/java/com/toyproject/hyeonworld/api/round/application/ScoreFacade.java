package com.toyproject.hyeonworld.api.round.application;

import com.toyproject.hyeonworld.api.answerSubmission.domain.AnswerSubmissionService;
import com.toyproject.hyeonworld.api.round.domain.RoundService;
import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundRankingCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundResultConfirmCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.out.RankStage;
import com.toyproject.hyeonworld.api.round.domain.dto.out.ResultStage;
import com.toyproject.hyeonworld.api.user.application.dto.NameScoreDto;
import com.toyproject.hyeonworld.api.score.domain.dto.out.ScoreInfo;
import com.toyproject.hyeonworld.api.round.event.ScoreEvent.Ranking;
import com.toyproject.hyeonworld.api.round.event.ScoreEventPublisher;
import com.toyproject.hyeonworld.api.score.domain.ScoreService;
import com.toyproject.hyeonworld.api.answerSubmission.domain.out.AnswerSubmissionInfos;
import com.toyproject.hyeonworld.api.user.domain.UserService;
import com.toyproject.hyeonworld.common.annotation.Facade;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
@Facade
@RequiredArgsConstructor
public class ScoreFacade {

    private final AnswerSubmissionService answerSubmissionService;
    private final UserService userService;
    private final RoundService roundService;
    private final ScoreService scoreService;

    private final ScoreEventPublisher scoreEventPublisher;

    public ResultStage result(long roundId) {
        AnswerSubmissionInfos answerSubmissionInfos = answerSubmissionService.retrieveAnswerSubmissions(roundId);
        String answer = roundService.retrieveAnswer(roundId);
        Set<Long> winnerIds = answerSubmissionInfos.getWinnerIds(answer);
        Map<Long, String> UsersIdName = userService.getNamesByIds(winnerIds);
        return ResultStage.from(answer, UsersIdName);
    }

    public ScoreInfo resultScore(RoundResultConfirmCommand command) {
        long partyId = roundService.retrievePartyId(command.roundId());
        command = new RoundResultConfirmCommand(partyId, command.roundId(), command.winners());

        ScoreInfo scoreInfo = scoreService.updateScore(command);
        return scoreInfo;
    }

    @Transactional
    public RankStage ranking(RoundRankingCommand command) {
        Map<Long, Long> userScores = scoreService.retrieveSumScores(command.partyId());
        scoreEventPublisher.execute(new Ranking(command.partyId(), userScores));

        List<NameScoreDto> nameScoreDtos = userScores.entrySet().stream()
                .map(entry -> NameScoreDto.from(userService.getNameById(entry.getKey()), entry.getValue()))
                .toList();

        return RankStage.from(nameScoreDtos);
    }
}
