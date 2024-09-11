package com.toyproject.hyeonworld.api.round.controller.dto.res;

import com.toyproject.hyeonworld.api.submission.controller.dto.res.SubmissionResponse;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionCheckInfo;
import com.toyproject.hyeonworld.api.submission.domain.dto.out.SubmissionCheckInfos;
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


    public static List<RoundSubmissionResponse.Basic> from(SubmissionCheckInfos submissionCheckInfos) {
      return submissionCheckInfos.stream()
          .map(RoundSubmissionResponse.Basic::from)
          .toList();
    }

    public static RoundSubmissionResponse.Basic from(SubmissionCheckInfo submissionCheckInfo) {
      return ObjectrMapper.convert(submissionCheckInfo, RoundSubmissionResponse.Basic.class);
    }

  }

  record Confirm (
      long gameId,
      long userId,
      String text,
      String number
  ){
    public static RoundSubmissionResponse.Confirm from(SubmissionCheckInfo submissionCheckInfo) {
      return ObjectrMapper.convert(submissionCheckInfo, RoundSubmissionResponse.Confirm.class);
    }
  }

  record Show(
      long userId,
      String text,
      String number
  ) {
    public static RoundSubmissionResponse.Confirm from(SubmissionCheckInfo submissionCheckInfo) {
      return ObjectrMapper.convert(submissionCheckInfo, RoundSubmissionResponse.Confirm.class);
    }
  }
}
