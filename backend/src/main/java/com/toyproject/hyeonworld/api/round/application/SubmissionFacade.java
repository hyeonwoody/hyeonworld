package com.toyproject.hyeonworld.api.round.application;

import static com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionInfo.*;

import com.toyproject.hyeonworld.api.game.domain.GameService;
import com.toyproject.hyeonworld.api.game.strategy.GameStrategy;
import com.toyproject.hyeonworld.api.round.domain.RoundService;
import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundPlayCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.in.SubmissionCheckConfirmCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.out.PlayInfo;
import com.toyproject.hyeonworld.api.round.domain.dto.out.RoundInfo;
import com.toyproject.hyeonworld.api.round.domain.dto.out.ShowStage;
import com.toyproject.hyeonworld.api.round.event.Submission.AnswerSubmissionEvent;
import com.toyproject.hyeonworld.api.round.event.Submission.PrimarySubmissionEvent;
import com.toyproject.hyeonworld.api.submission.domain.SubmissionService;
import com.toyproject.hyeonworld.api.submission.domain.dto.in.RoundSubmissionCommand;
import com.toyproject.hyeonworld.api.submission.domain.dto.in.SubmissionCommand;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.RoundSubmissionInfo;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.RoundSubmissionInfos;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionInfo;
import com.toyproject.hyeonworld.api.round.event.Submission.SubmissionEventPublisher;
import com.toyproject.hyeonworld.api.user.domain.UserService;
import com.toyproject.hyeonworld.common.annotation.Facade;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 7.
 */
@Facade
@RequiredArgsConstructor
public class SubmissionFacade {

    private final GameService gameService;
    private final RoundService roundService;
    private final SubmissionService submissionService;
    private final UserService userService;
    private final SubmissionEventPublisher submissionEventPublisher;

    @Transactional
    public SubmissionInfo submitSubmission(SubmissionCommand command) {
        RoundInfo roundInfo = roundService.retrieveCurrentRound(command.partyId());
        submissionEventPublisher.execute(PrimarySubmissionEvent.from(roundInfo.getId(), command));
        return from(roundInfo.getId(), command);
    }

    @Transactional
    public RoundSubmissionInfos check(RoundSubmissionCommand command) {
        RoundSubmissionInfos roundSubmissionInfos = submissionService.check(command.roundId());

        for (RoundSubmissionInfo roundSubmissionInfo : roundSubmissionInfos) {
            String userName = userService.getNameById(roundSubmissionInfo.getUserId());
            roundSubmissionInfo.complete(userName);
        }
        return roundSubmissionInfos;
    }

    @Transactional
    public RoundSubmissionInfo checkConfirm(SubmissionCheckConfirmCommand command) {
        GameStrategy gameStrategy = gameService.getGame(command.gameId());
        gameStrategy.checkConfirm(command);
        return new RoundSubmissionInfo(command);
    }

    public ShowStage show(long roundId) {
        long gameId = roundService.retrieveCurrentGame(roundId);
        GameStrategy gameStrategy = gameService.getGame(gameId);
        return ShowStage.from(gameStrategy.showStage(roundId));
    }

    @Transactional
    public PlayInfo play(RoundPlayCommand command) {
        RoundInfo roundInfo = roundService.retrieveCurrentRound(command.partyId());
        long gameId = roundService.retrieveCurrentGame(roundInfo.getId());
        GameStrategy gameStrategy = gameService.getGame(gameId);
        submissionEventPublisher.execute(AnswerSubmissionEvent.from(gameStrategy, command));
        return PlayInfo.from(command);
    }
}
