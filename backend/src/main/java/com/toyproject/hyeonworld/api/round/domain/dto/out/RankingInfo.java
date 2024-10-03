package com.toyproject.hyeonworld.api.round.domain.dto.out;

import com.toyproject.hyeonworld.api.round.domain.dto.out.ResultInfo.Winner;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 10. 1.
 */
@Getter
public class RankingInfo {

  private PriorityQueue<Participant> participants;

  public RankingInfo() {

    this.participants = new PriorityQueue<Participant>(
        (a,b) -> Long.compare(b.getScore(), a.getScore())
    );
  }

  @Getter
  public static class Participant {


    private final String name;
    private final long score;

    public Participant(String name, long score) {
      this.name = name;
      this.score = score;
    }
  }

  public void addParticipant(String name, long score) {
    Participant participant = new Participant(name, score);
    participants.add(participant);
  }
}
