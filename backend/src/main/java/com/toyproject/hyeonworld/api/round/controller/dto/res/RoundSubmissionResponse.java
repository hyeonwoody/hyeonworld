package com.toyproject.hyeonworld.api.round.controller.dto.res;

import com.toyproject.hyeonworld.api.round.domain.dto.out.ShowStage;
import com.toyproject.hyeonworld.api.submission.controller.dto.res.SubmissionResponse;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.RoundSubmissionInfo;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.RoundSubmissionInfos;
import com.toyproject.hyeonworld.common.mapper.ObjectrMapper;
import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 8.
 */
public interface RoundSubmissionResponse extends SubmissionResponse {
  long userId();
  String name();
  String text();
  String number();

  record Basic(
      long userId,
      String name,
      String text,
      String number
  ) implements SubmissionResponse {


    public static List<RoundSubmissionResponse.Basic> from(
        RoundSubmissionInfos roundSubmissionInfos) {
      return roundSubmissionInfos.stream()
          .map(RoundSubmissionResponse.Basic::from)
          .toList();
    }

    public static RoundSubmissionResponse.Basic from(RoundSubmissionInfo roundSubmissionInfo) {
      return ObjectrMapper.convert(roundSubmissionInfo, RoundSubmissionResponse.Basic.class);
    }

  }

  record Confirm (
      long gameId,
      long userId,
      String text,
      String number
  ){
    public static RoundSubmissionResponse.Confirm from(RoundSubmissionInfo roundSubmissionInfo) {
      return ObjectrMapper.convert(roundSubmissionInfo, RoundSubmissionResponse.Confirm.class);
    }
  }

  record Show(
      String content
  ) {
    public static RoundSubmissionResponse.Show from(ShowStage showStage) {
      return ObjectrMapper.convert(showStage, Show.class);
    }
  }
}
