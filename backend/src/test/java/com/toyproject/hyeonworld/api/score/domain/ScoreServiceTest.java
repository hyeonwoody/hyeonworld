package com.toyproject.hyeonworld.api.score.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundResultConfirmCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundResultConfirmCommand.Winner;
import com.toyproject.hyeonworld.api.score.infarstructure.ScoreHistoryRepository;
import com.toyproject.hyeonworld.api.user.application.dto.ScoreDto;
import com.toyproject.hyeonworld.api.user.application.dto.ScoreDtos;
import com.toyproject.hyeonworld.api.score.domain.dto.out.ScoreInfo;
import com.toyproject.hyeonworld.api.score.infarstructure.ScoreRepository;
import com.toyproject.hyeonworld.api.score.infarstructure.entity.Score;
import com.toyproject.hyeonworld.api.score.infarstructure.entity.ScoreHistory;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

/**
* @author : hyeonwoody@gmail.com
* @since : 24. 10. 6.
*/
class ScoreServiceTest {
  private final ScoreRepository scoreRepository = Mockito.mock(ScoreRepository.class);
  private final ScoreHistoryRepository scoreHistoryRepository = Mockito.mock(ScoreHistoryRepository.class);
  private final ScoreService service = new ScoreService(scoreRepository, scoreHistoryRepository);

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


    when(scoreHistoryRepository.saveAll(any())).thenAnswer(invocation ->
    {
      List<ScoreHistory> savedScore = invocation.getArgument(0);
      return savedScore;
    });

    ScoreInfo result = service.updateScore(command);
    assertNotNull(result);
    ArgumentCaptor<List<ScoreHistory>> scoreHistoryCaptor = ArgumentCaptor.forClass(List.class);
    verify(scoreHistoryRepository, times(1)).saveAll(scoreHistoryCaptor.capture());
    List<ScoreHistory> capturedScore = scoreHistoryCaptor.getValue();

    assertEquals(2, capturedScore.size());
    for (int i = 0; i < capturedScore.size(); ++i){
      Winner winner =winners.get(i);
      ScoreHistory scoreHistory = capturedScore.get(i);

      assertEquals(winner.id(), scoreHistory.getUserId());
      assertEquals(winner.score(), scoreHistory.getScore());
    }

    //verify(scoreRepository, times(1)).saveScoreHistoryAll(any());
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
    when(scoreHistoryRepository.findByPartyId(partyId)).thenReturn(mockScoreHistories);
    ScoreDtos result = service.retrieveScores(partyId);
    assertNotNull(result);
    verify(scoreHistoryRepository, times(1)).findByPartyId(partyId);
    for (int i = 0; i < result.size(); ++i){
      ScoreDto userScore = result.get(i);
      ScoreHistory scoreHistory = mockScoreHistories.get(i);

      assertEquals(scoreHistory.getUserId(), userScore.userId());
      assertEquals(scoreHistory.getScore(), userScore.score());
    }
  }

  @Test
  void retrieveSumScores() {
    List<ScoreHistory> mockScoreHistories = Arrays.asList(
        ScoreHistory.defaultBuilder()
            .userId(1L)
            .score(10L)
            .build(),
        ScoreHistory.defaultBuilder()
            .userId(2L)
            .score(20L)
            .build(),
        ScoreHistory.defaultBuilder()
            .userId(1L)
            .score(15L)
            .build()
    );
    when(scoreHistoryRepository.findByPartyId(partyId)).thenReturn(mockScoreHistories);

    // Act
    HashMap<Long, Long> result = service.retrieveSumScores(partyId);
    assertNotNull(result);
    assertEquals(2, result.size());

    Map<Long, Long> expectedSums = new HashMap<>();
    for (ScoreHistory history : mockScoreHistories) {
      expectedSums.merge(history.getUserId(), history.getScore(), Long::sum);
    }

    for (Map.Entry<Long, Long>entry : result.entrySet()){
      Long userId = entry.getKey();
      Long totalScore = entry.getValue();
      assertTrue(expectedSums.containsKey(userId));
      assertEquals(expectedSums.get(userId), totalScore);
    }

    for (long userId : expectedSums.keySet()){
      assertTrue(result.containsKey(userId));
    }
    verify(scoreHistoryRepository, times(1)).findByPartyId(partyId);
  }

  @Test
  void save() {
    Map<Long, Long> userSumScore = new HashMap<>();
    userSumScore.put(1L, 100L);
    userSumScore.put(2L, 200L);

    service.save(partyId, userSumScore);
    ArgumentCaptor<List<Score>> scoreInfoCaptor = ArgumentCaptor.forClass(List.class);
    verify(scoreRepository).saveAll(scoreInfoCaptor.capture());
    List<Score> capturedScoreInfos = scoreInfoCaptor.getValue();
    for (Score score : capturedScoreInfos){
      assertTrue(userSumScore.containsKey(score.getUserId()));
      assertEquals(userSumScore.get(score.getUserId()), score.getScore());
    }
    Set<Long> savedUserIds = capturedScoreInfos.stream().map(Score::getUserId).collect(Collectors.toSet());
    assertEquals(userSumScore.keySet(), savedUserIds);
  }
}