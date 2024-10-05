package com.toyproject.hyeonworld.api.round.domain.dto.out;

import com.toyproject.hyeonworld.api.round.infrastructure.entity.Round;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 10. 3.
 */
public class RoundInfoTest {

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
    Round mockRound = Round.builder()
        .id(-1L)
        .partyId(partyId)
        .gameId(gameId)
        .answer("")
        .createdAt(LocalDateTime.now())
        .terminatedAt(null)
        .build();
    RoundInfo expectedRoundInfo = new RoundInfo();
    expectedRoundInfo.id = -1L;
    expectedRoundInfo.answer = "";

    //when
    RoundInfo result = RoundInfo.from(mockRound);

    //then
    assertNotNull(result);
    assertEquals(expectedRoundInfo.getId(), result.getId());
    assertEquals(expectedRoundInfo.getAnswer(), result.getAnswer());
  }

  @Test
  void getPartyIdFrom() {
    //given
    Round mockRound = Round.builder()
        .id(-1L)
        .partyId(partyId)
        .gameId(gameId)
        .answer("")
        .createdAt(LocalDateTime.now())
        .terminatedAt(null)
        .build();
    RoundInfo expectedRoundInfo = new RoundInfo();
    expectedRoundInfo.id = -1L;
    expectedRoundInfo.answer = "";

    //when
    long result = RoundInfo.getPartyIdFrom(mockRound);

    //then
    assertEquals(result, mockRound.getPartyId());
  }

  @Test
  void getGameIdFrom() {
    //given
    Round mockRound = Round.builder()
        .id(-1L)
        .partyId(partyId)
        .gameId(gameId)
        .answer("")
        .createdAt(LocalDateTime.now())
        .terminatedAt(null)
        .build();
    RoundInfo expectedRoundInfo = new RoundInfo();
    expectedRoundInfo.id = -1L;
    expectedRoundInfo.answer = "";

    //when
    long result = RoundInfo.getGameIdFrom(mockRound);

    //then
    assertEquals(result, mockRound.getGameId());
  }

  @Test
  void getAnswerFrom() {
    //given
    Round mockRound = Round.builder()
        .id(-1L)
        .partyId(partyId)
        .gameId(gameId)
        .answer("")
        .createdAt(LocalDateTime.now())
        .terminatedAt(null)
        .build();
    RoundInfo expectedRoundInfo = new RoundInfo();
    expectedRoundInfo.id = -1L;
    expectedRoundInfo.answer = "";

    //when
    String result = RoundInfo.getAnswerFrom(mockRound);

    //then
    assertEquals(result, mockRound.getAnswer());
  }

  @Test
  void  entityToUpdateAnswerString(){
    RoundInfo roundInfo = new RoundInfo();
    roundInfo.id = -1L;
    roundInfo.answer = "";
    String answer = "prison_break";
    Round round = roundInfo.entityToUpdateAnswer(answer);
    assertEquals(answer, round.getAnswer());
  }

  @Test
  void  entityToUpdateAnswerLong(){
    RoundInfo roundInfo = new RoundInfo();
    roundInfo.id = -1L;
    roundInfo.answer = "";
    long answer = 30;
    Round round = roundInfo.entityToUpdateAnswer(answer);
    assertEquals(String.valueOf(answer), round.getAnswer());
  }
}
