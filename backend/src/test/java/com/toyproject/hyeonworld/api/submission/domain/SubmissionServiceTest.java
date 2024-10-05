package com.toyproject.hyeonworld.api.submission.domain;

import static org.mockito.Mockito.when;

import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundPlayCommand;
import com.toyproject.hyeonworld.api.submission.domain.SubmissionService;
import com.toyproject.hyeonworld.api.submission.domain.dto.in.SubmissionCommand;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.AnswerSubmissionInfo;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.AnswerSubmissionInfos;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.RoundSubmissionInfo;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.RoundSubmissionInfos;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionInfo;
import com.toyproject.hyeonworld.api.submission.infrastructure.SubmissionRepository;
import com.toyproject.hyeonworld.api.submission.infrastructure.entity.AnswerSubmission;
import com.toyproject.hyeonworld.api.submission.infrastructure.entity.Submission;
import com.toyproject.hyeonworld.common.exception.ServerException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 10. 4.
 */
class SubmissionServiceTest {
  private final SubmissionRepository submissionRepository = Mockito.mock(SubmissionRepository.class);
  private final SubmissionService service = new SubmissionService(submissionRepository);

  private long partyId;
  private long roundId;
  private long submissionId;

  @BeforeEach
  void setUp() {
    partyId = 4L;
    roundId = 20L;
    submissionId = 58L;
  }

  @Test
  void retrieveById() {
    long retrieveSubmissionId = submissionId;
    long retrireveRoundId = roundId;
    Submission mockSubmission = Submission.defaultBuilder()
                              .id(submissionId)
                              .roundId(retrireveRoundId)
                              .userId(4L)
                              .number(3L)
                              .text("prison_break")
                              .createdAt(LocalDateTime.now())
                              .build();

    when(submissionRepository.findById(retrieveSubmissionId)).thenReturn(Optional.of(mockSubmission));

    RoundSubmissionInfo roundSubmissionInfo = service.retrieveById(retrieveSubmissionId);
    verify(submissionRepository, times(1)).findById(retrieveSubmissionId);
    assertNotNull(roundSubmissionInfo);
    assertEquals(mockSubmission.getUserId(), roundSubmissionInfo.getUserId());
    assertEquals(null, roundSubmissionInfo.getName());
    assertEquals(mockSubmission.getText(), roundSubmissionInfo.getText());
    assertEquals(mockSubmission.getNumber(), roundSubmissionInfo.getNumber());
  }
      @Test
      void retrieveById_WhenSubmissionNotFound() {
        long nonExistentSubmissionId = -1L;
        when(submissionRepository.findById(nonExistentSubmissionId)).thenReturn(Optional.empty());

        assertThrows(ServerException.class, () -> service.retrieveById(nonExistentSubmissionId));

        verify(submissionRepository, times(1)).findById(nonExistentSubmissionId);
      }

  @Test
  void retrieveByUserId() {
    long retrieveUserId = 2L;
    Submission mockSubmission = Submission.defaultBuilder()
        .id(submissionId)
        .roundId(roundId)
        .userId(retrieveUserId)
        .number(3L)
        .text("prison_break")
        .createdAt(LocalDateTime.now())
        .build();
    when(submissionRepository.findMostRecentByUserId(retrieveUserId)).thenReturn(mockSubmission);

    SubmissionInfo submissionInfo = service.retrieveByUserId(retrieveUserId);
    verify(submissionRepository, times(1)).findMostRecentByUserId(retrieveUserId);
    assertNotNull(submissionInfo);
    assertEquals(submissionId, submissionInfo.getId());
    assertEquals(0, submissionInfo.getPartyId());
    assertEquals(roundId, submissionInfo.getRoundId());
  }


  @Test
  void hand() {
    long retrieveUserId = 2L;
    SubmissionCommand command = new SubmissionCommand(partyId, retrieveUserId, "prison_break", 33);
    Submission mockSubmission = Submission.defaultBuilder()
        .id(submissionId)
        .roundId(roundId)
        .userId(command.userId())
        .text(command.text())
        .number(command.number())
        .build();
    when(submissionRepository.save(any(Submission.class))).thenReturn(mockSubmission);

    SubmissionInfo submissionInfo = service.hand(roundId, command);
    verify(submissionRepository, times(1)).save(any(Submission.class));
    assertNotNull(submissionInfo);
    assertEquals(0, submissionInfo.getId()); //
    assertEquals(partyId, submissionInfo.getPartyId());
    assertEquals(roundId, submissionInfo.getRoundId());
  }

  @Test
  void check() {

    List<Submission> mockSubmissions = Arrays.asList(
        Submission.defaultBuilder()
            .id(submissionId)
            .roundId(roundId)
            .userId(-101L)
            .text("prison_break")
            .number(1L)
            .build(),
        Submission.defaultBuilder()
            .id(submissionId+1)
            .roundId(roundId)
            .userId(-102L)
            .text("lost")
            .number(2L)
            .build()
    );
    List<String> expectedTexts = Arrays.asList("prison_break", "lost");
    when(submissionRepository.findMostRecentByRoundId(roundId)).thenReturn(mockSubmissions);

    RoundSubmissionInfos roundSubmissionInfos = service.check(roundId);
    assertNotNull(roundSubmissionInfos);
    assertEquals(2, roundSubmissionInfos.size());
    verify(submissionRepository, times(1)).findMostRecentByRoundId(roundId);

    for (int i = 0; i < roundSubmissionInfos.size(); ++i){
      RoundSubmissionInfo submission = roundSubmissionInfos.get(i);
      //assertEquals(expectedIds.get(i), submission.getId()); //
      assertEquals(-101L - i, submission.getUserId());
      assertEquals(null, submission.getName());
      assertEquals(1L + i, submission.getNumber());
      assertEquals(expectedTexts.get(i), submission.getText());
    }
  }

  @Test
  void submitAnswer() {
    long retrieveUserId = 2L;
    RoundPlayCommand command = new RoundPlayCommand(partyId, retrieveUserId, "prison_break");
    AnswerSubmission mockAnswerSubmission = AnswerSubmission.builder()
        .roundId(roundId)
        .userId(command.userId())
        .answer(command.answer())
        .build();
    when(submissionRepository.saveAnswer(any(AnswerSubmission.class))).thenReturn(mockAnswerSubmission);

    AnswerSubmissionInfo result = service.submitAnswer(roundId, command);

    assertNotNull(result);
    //assertEquals(expectedInfo.getId(), result.getId()); //
    assertEquals(roundId, result.getRoundId());
    assertEquals(command.userId(), result.getUserId());
    assertEquals(command.answer(), result.getAnswer());
  }

  @Test
  void retrieveAnswerSubmissions() {


    List<AnswerSubmission> mockAnswerSubmissions = Arrays.asList(
        AnswerSubmission.builder()
            .roundId(roundId)
            .userId(1L)
            .answer("prison_break").build(),
        AnswerSubmission.builder()
            .roundId(roundId)
            .userId(2L)
            .answer("lost").build()
    );
    when(submissionRepository.findAnswerMostRecentByRoundId(roundId)).thenReturn(mockAnswerSubmissions);


    AnswerSubmissionInfos result = service.retrieveAnswerSubmissions(roundId);
    assertNotNull(result);
    for (int i = 0; i < result.size(); ++i){
      AnswerSubmissionInfo info = result.get(i);
      assertEquals(roundId, info.getRoundId());
      assertEquals(1L+i, info.getUserId());

      AnswerSubmission answerSubmission = mockAnswerSubmissions.get(i);
      assertEquals(answerSubmission.getAnswer(), info.getAnswer());
    }
  }
}