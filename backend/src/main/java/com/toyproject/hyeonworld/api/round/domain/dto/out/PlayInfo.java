package com.toyproject.hyeonworld.api.round.domain.dto.out;

import com.toyproject.hyeonworld.api.round.domain.dto.in.RoundPlayCommand;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
@Getter
public class PlayInfo {
  private long userId;
  public static PlayInfo from(RoundPlayCommand command) {
    return ObjectrMapper.convert(command, PlayInfo.class);
  }
}
