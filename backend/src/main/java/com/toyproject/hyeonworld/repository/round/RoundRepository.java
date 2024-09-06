package com.toyproject.hyeonworld.repository.round;


import com.toyproject.hyeonworld.api.round.infrastructure.entity.Round;
import org.springframework.stereotype.Repository;

@Repository
public interface RoundRepository  {

  Round save(Round round);
}
