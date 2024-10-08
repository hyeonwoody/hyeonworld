package com.toyproject.hyeonworld.api.round.domain;

import static org.mockito.ArgumentMatchers.any;

import com.toyproject.hyeonworld.api.round.domain.dto.in.BeginRoundCommand;
import com.toyproject.hyeonworld.api.round.domain.dto.out.RoundInfo;
import com.toyproject.hyeonworld.api.round.infrastructure.RoundRepository;
import com.toyproject.hyeonworld.api.round.infrastructure.entity.Round;

import com.toyproject.hyeonworld.common.exception.ServerException;
import com.toyproject.hyeonworld.common.exception.dto.ServerResponseCode;
import java.time.LocalDateTime;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 10. 3.
 */
class RoundServiceTest {
  private final RoundRepository roundRepository = Mockito.mock(RoundRepository.class);
  private final RoundService service = new RoundService(roundRepository);

  private long partyId;
  private long gameId;

  @BeforeEach
  void setUp() {
    partyId = 4L;
    gameId = 1L;
  }

  @Test
  void begin() {
    //given
    BeginRoundCommand command = new BeginRoundCommand(partyId, gameId);
    Round mockRound = Round.builder()
        .id(-1L)
        .partyId(partyId)
        .gameId(gameId)
        .answer("")
        .createdAt(LocalDateTime.now())
        .terminatedAt(null)
        .build();
    when(roundRepository.insert(any(Round.class))).thenReturn(mockRound);

    //when
    RoundInfo roundInfo = service.begin(command);

    //then
    verify(roundRepository, times(1)).insert(any(Round.class));
    assertEquals(-1, roundInfo.getId());
    assertEquals("",roundInfo.getAnswer());
  }

    @Test
    void begin_shouldHandleNullCommand(){
      BeginRoundCommand command = null;
      assertThrows(NullPointerException.class, ()->service.begin(command));
      verify(roundRepository, never()).insert(any());
    }

  @Test
  void retrieveCurrentRound() {
    //given
    long retrievePartyId = 60L;
    Round mockRound = Round.builder()
        .id(-1L)
        .partyId(retrievePartyId)
        .gameId(gameId)
        .answer("")
        .createdAt(LocalDateTime.now())
        .terminatedAt(null)
        .build();
    when(roundRepository.findByPartyId(retrievePartyId)).thenReturn(Optional.of(mockRound));

    //when
    RoundInfo roundInfo = service.retrieveCurrentRound(retrievePartyId);
    verify(roundRepository, times(1)).findByPartyId(retrievePartyId);
    assertEquals(-1, roundInfo.getId());
    assertEquals("",roundInfo.getAnswer());
  }

    @Test
    void retrieveCurrentRound_shouldThrowExceptionWhenRoundNotFound(){
      long nonExistentPartyId = -1L;
      when(roundRepository.findByPartyId(nonExistentPartyId)).thenReturn(Optional.empty());

      ServerException exception = assertThrows(ServerException.class,
          ()-> service.retrieveCurrentRound(nonExistentPartyId));

      assertEquals(ServerResponseCode.ROUND_NOT_FOUND,
          exception.getCode());
      verify(roundRepository, times(1)).findByPartyId(nonExistentPartyId);
    }

  @Test
  void updateAnswerStringInternal() {
    //given
    long retrievePartyId = 60L;
    long retrieveRoundId = 30L;
    Round mockRound = Round.builder()
        .id(retrieveRoundId)
        .partyId(retrievePartyId)
        .gameId(gameId)
        .answer("")
        .createdAt(LocalDateTime.now())
        .terminatedAt(null)
        .build();
    when(roundRepository.findById(retrieveRoundId)).thenReturn(Optional.of(mockRound));
    String text = "text";
    RoundInfo roundInfo = new RoundInfo();
    Round updatedMockRound = Round.builder()
        .id(retrieveRoundId)
        .partyId(retrievePartyId)
        .gameId(gameId)
        .answer(text)
        .createdAt(LocalDateTime.now())
        .terminatedAt(null)
        .build();
    when(roundRepository.update(updatedMockRound)).thenReturn(mockRound);


    //when
    RoundInfo ret = service.updateAnswerInternal(retrieveRoundId, text);

    //then
    verify(roundRepository, times(1)).findById(retrieveRoundId);
    verify(roundRepository, times(1)).update(any(Round.class));
  }

    @Test
    void updateAnswerStringInternal_shouldThrowExceptionWhenRoundNotFound(){
      long nonExistentRoundId = -1L;
      String answer = "non";
      when(roundRepository.findById(nonExistentRoundId)).thenReturn(Optional.empty());

      ServerException exception = assertThrows(ServerException.class,
          ()-> service.updateAnswerInternal(nonExistentRoundId, answer));

      assertEquals(ServerResponseCode.ROUND_NOT_FOUND,
          exception.getCode());
      verify(roundRepository, times(1)).findById(nonExistentRoundId);
    }

  @Test
  void updateAnswerLongInternal() {
    //given
    long retrievePartyId = 60L;
    long retrieveRoundId = 30L;
    Round mockRound = Round.builder()
        .id(retrieveRoundId)
        .partyId(retrievePartyId)
        .gameId(gameId)
        .answer("")
        .createdAt(LocalDateTime.now())
        .terminatedAt(null)
        .build();

    when(roundRepository.findById(retrieveRoundId)).thenReturn(Optional.of(mockRound));
    long number = 20;

    RoundInfo roundInfo = new RoundInfo();
    Round updatedMockRound = Round.builder()
        .id(-1L)
        .partyId(retrievePartyId)
        .gameId(gameId)
        .answer(String.valueOf(number))
        .createdAt(LocalDateTime.now())
        .terminatedAt(null)
        .build();

    when(roundRepository.update(updatedMockRound)).thenReturn(mockRound);


    //when
    RoundInfo ret = service.updateAnswerInternal(retrieveRoundId, number);

    //then
    verify(roundRepository, times(1)).findById(retrieveRoundId);
    verify(roundRepository, times(1)).update(any(Round.class));
  }

    @Test
    void updateAnswerLongInternal_shouldThrowExceptionWhenRoundNotFound(){
      long nonExistentRoundId = -1L;
      Long answer = 3L;
      when(roundRepository.findById(nonExistentRoundId)).thenReturn(Optional.empty());

      ServerException exception = assertThrows(ServerException.class,
          ()-> service.updateAnswerInternal(nonExistentRoundId, answer));

      assertEquals(ServerResponseCode.ROUND_NOT_FOUND,
          exception.getCode());
      verify(roundRepository, times(1)).findById(nonExistentRoundId);
    }
//  @Test void updateAnswerString() {}
//  @Test void updateAnswerLong() {}


  @Test
  void retrieveCurrentGame() {
    //given
    long retrieveRoundId = 30L;
    Round mockRound = Round.builder()
        .id(retrieveRoundId)
        .partyId(60L)
        .gameId(gameId)
        .answer("")
        .createdAt(LocalDateTime.now())
        .terminatedAt(null)
        .build();
    when(roundRepository.findById(retrieveRoundId)).thenReturn(Optional.of(mockRound));

    //when
    long retGameId = service.retrieveCurrentGame(retrieveRoundId);

    //then
    verify(roundRepository, times(1)).findById(retrieveRoundId);
    assertEquals(gameId, retGameId);
  }

    @Test
    void retrieveCurrentGame_shouldThrowExceptionWhenRoundNotFound() {
      // given
      long nonExistentRoundId = -1L;
      when(roundRepository.findById(nonExistentRoundId)).thenReturn(Optional.empty());

      // when & then
      ServerException exception = assertThrows(ServerException.class,
          () -> service.retrieveCurrentGame(nonExistentRoundId));

      assertEquals(ServerResponseCode.ROUND_NOT_FOUND, exception.getCode());
      verify(roundRepository, times(1)).findById(nonExistentRoundId);
    }


  @Test
  void retrieveAnswer() {
    //given
    long retrieveRoundId = 30L;
    String answer = "this is answer";
    Round mockRound = Round.builder()
        .id(retrieveRoundId)
        .partyId(60L)
        .gameId(gameId)
        .answer(answer)
        .createdAt(LocalDateTime.now())
        .terminatedAt(null)
        .build();
    when(roundRepository.findById(retrieveRoundId)).thenReturn(Optional.of(mockRound));

    //when
    String retAnswer = service.retrieveAnswer(retrieveRoundId);

    //then
    verify(roundRepository, times(1)).findById(retrieveRoundId);
    assertEquals(answer, retAnswer);
  }
    @Test
    void retrieveAnswer_shouldThrowExceptionWhenRoundNotFound() {
      // given
      long nonExistentRoundId = -1L;
      when(roundRepository.findById(nonExistentRoundId)).thenReturn(Optional.empty());

      // when & then
      ServerException exception = assertThrows(ServerException.class,
          () -> service.retrieveAnswer(nonExistentRoundId));

      assertEquals(ServerResponseCode.ROUND_NOT_FOUND, exception.getCode());
      verify(roundRepository, times(1)).findById(nonExistentRoundId);
    }


  @Test
  void retrievePartyId() {
    //given
    long retrievePartyId = 60L;
    long retrieveRoundId = 30L;
    Round mockRound = Round.builder()
        .id(retrieveRoundId)
        .partyId(retrievePartyId)
        .gameId(gameId)
        .answer("")
        .createdAt(LocalDateTime.now())
        .terminatedAt(null)
        .build();
    when(roundRepository.findById(retrieveRoundId)).thenReturn(Optional.of(mockRound));

    //when
    long retPartyId = service.retrievePartyId(retrieveRoundId);

    //then
    verify(roundRepository, times(1)).findById(retrieveRoundId);
    assertEquals(retrievePartyId, retPartyId);
  }

    @Test
    void retrievePartyId_shouldThrowExceptionWhenRoundNotFound() {
      // given
      long nonExistentRoundId = -1L;
      when(roundRepository.findById(nonExistentRoundId)).thenReturn(Optional.empty());

      // when & then
      ServerException exception = assertThrows(ServerException.class,
          () -> service.retrievePartyId(nonExistentRoundId));

      assertEquals(ServerResponseCode.ROUND_NOT_FOUND, exception.getCode());
      verify(roundRepository, times(1)).findById(nonExistentRoundId);
    }
}