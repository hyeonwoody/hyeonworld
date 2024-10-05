package com.toyproject.hyeonworld.api.score.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundResultConfirmCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundResultConfirmCommand.Winner;
import com.toyproject.hyeonworld.api.round.domain.dto.out.UserScoreInfo;
import com.toyproject.hyeonworld.api.round.domain.dto.out.UserScoreInfos;
import com.toyproject.hyeonworld.api.score.domain.dto.out.ScoreInfo;
import com.toyproject.hyeonworld.api.score.domain.dto.out.ScoreHistoryInfo;
import com.toyproject.hyeonworld.api.score.infarstructure.ScoreRepository;
import com.toyproject.hyeonworld.api.score.infarstructure.entity.ScoreHistory;
import com.toyproject.hyeonworld.api.submission.domain.SubmissionService;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.RoundSubmissionInfo;
import com.toyproject.hyeonworld.api.submission.infrastructure.SubmissionRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
* @author : hyeonwoody@gmail.com
* @since : 24. 10. 6.
*/class ScoreServiceTest {
  private final ScoreRepository scoreRepository = Mockito.mock(ScoreRepository.class);
  private final ScoreService service = new ScoreService(scoreRepository);

  private long partyId;
  private long roundId;
  private long submissionId;

  @BeforeEach
  void setUp() {
    partyId = 4L;
    roundId = 20L;
    submissionId = 58L;
  }

  @Test void submitAnswer() {}

  @Test
  void updateScore() {
    List<Winner> winners = Arrays.asList(
        new RoundResultConfirmCommand.Winner(1L, 100L),
        new RoundResultConfirmCommand.Winner(2L, 200L)
    );
    RoundResultConfirmCommand command = new RoundResultConfirmCommand(partyId, roundId, winners);
    List<ScoreHistory> mockScoreHistory = ScoreHistoryInfo.createEntities(command);
    when(scoreRepository.saveScoreHistoryAll(any())).thenReturn(mockScoreHistory);

    ScoreInfo result = service.updateScore(command);

    assertNotNull(result);
    verify(scoreRepository, times(1)).saveScoreHistoryAll(any());
  }

  @Test
  void retrieveScores() {
    List<ScoreHistory> mockScoreHistories = Arrays.asList(
        ScoreHistory.defaultBuilder()
            .userId(1L)
            .score(10L)
            .build(),
        ScoreHistory.defaultBuilder()
            .userId(2L)
            .score(10L)
            .build()
    );
    when(scoreRepository.findByPartyId(partyId)).thenReturn(mockScoreHistories);
    UserScoreInfos result = service.retrieveScores(partyId);
    assertNotNull(result);
    verify(scoreRepository, times(1)).findByPartyId(partyId);
    for (int i = 0; i < result.size(); ++i){
      UserScoreInfo userScore = result.get(i);
      ScoreHistory scoreHistory = mockScoreHistories.get(i);

      assertEquals(scoreHistory.getUserId(), userScore.getUserId());
      assertEquals(scoreHistory.getScore(), userScore.getScore());
    }
  }

  @Test
  void retrieveSumScores() {
  }

  @Test
  void save() {
  }
}