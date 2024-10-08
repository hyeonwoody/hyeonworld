package com.toyproject.hyeonworld.api.round.controller.dto.res;

import com.toyproject.hyeonworld.api.round.domain.dto.out.PlayInfo;
import com.toyproject.hyeonworld.api.round.domain.dto.out.RankingInfo;
import com.toyproject.hyeonworld.api.round.domain.dto.out.ResultInfo;
import com.toyproject.hyeonworld.api.round.domain.dto.out.RoundInfo;

import com.toyproject.hyeonworld.api.score.domain.dto.out.ScoreInfo;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;


/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 6.
 */
public abstract interface RoundResponse {


  long getId();

  record Begin (
      long id
  ) {
    public static RoundResponse.Begin from (RoundInfo roundInfo){
      return ObjectrMapper.convert(roundInfo, Begin.class);
    }
  }
  record Play (
      long userId
  ) {
    public static RoundResponse.Play from (PlayInfo playInfo){
      return ObjectrMapper.convert(playInfo, Play.class);
    }
  }

  record Result (
      String answer,
      List<Winner> winners
  ) {
    record Winner (
        Long id,
        String name
    ) {

      public static Winner from(Long userId, String userName) {
        if (userId == null) {
          throw new IllegalArgumentException("User ID cannot be null");
        }
        if (userName == null || userName.trim().isEmpty()) {
          throw new IllegalArgumentException("User name cannot be null or empty");
        }
        return new Winner(userId, userName);
      }
    }
    public static RoundResponse.Result from (ResultInfo resultInfo){
      return new Result(resultInfo.getAnswer(), convertWinners(resultInfo.getWinners()));
    }

    private static List<RoundResponse.Result.Winner> convertWinners(List<ResultInfo.Winner> winners) {
      if (winners == null) {
        throw new IllegalArgumentException("Winner IDs and names must be non-null and have the same size");
      }
      return winners.stream()
          .map(winner -> RoundResponse.Result.Winner.from(winner.getId(), winner.getName()))
          .toList();
    }
  }
  record ResultScore (
  ) {


    public static RoundResponse.ResultScore from(ScoreInfo scoreInfo) {
      return new ResultScore();
    }
  }


  record Ranking(
      List<Participant> rank
  ) {
    record Participant (
        String name,
        Long score
    ){
      public static Ranking.Participant from(String userName, long score) {
        if (userName == null || userName.trim().isEmpty()) {
          throw new IllegalArgumentException("User name cannot be null or empty");
        }
        return new Participant(userName, score);
      }
    }
    public static RoundResponse.Ranking from (RankingInfo rankingInfo){
      return new Ranking(convertParticipant(rankingInfo.getParticipants()));
    }

    private static List<Participant> convertParticipant(PriorityQueue<RankingInfo.Participant> pq) {
      
      return Stream.generate(pq::poll)
          .limit(pq.size())
          .map(participant -> RoundResponse.Ranking.Participant.from(participant.getName(), participant.getScore()))
          .toList();
    }

    private static List<Participant> convertParticipant(List<RankingInfo.Participant> participants) {
      if (participants == null) {
        throw new IllegalArgumentException("Winner IDs and names must be non-null and have the same size");
      }
      return participants.stream()
          .map(participant -> RoundResponse.Ranking.Participant.from(participant.getName(), participant.getScore()))
          .toList();
    }

    ;
  }

}
