package com.toyproject.hyeonworld.api.round.domain.dto.out;

import com.toyproject.hyeonworld.api.score.infarstructure.entity.ScoreHistory;
import com.toyproject.hyeonworld.api.user.domain.dto.out.UserInfo;
import com.toyproject.hyeonworld.api.user.infrastructure.entity.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 10. 1.
 */
public class UserScoreInfos extends ArrayList<UserScoreInfo> {

  public UserScoreInfos (List<UserScoreInfo> userScoreInfos) {
    super(userScoreInfos != null ? userScoreInfos : Collections.emptyList());
  }

  public static UserScoreInfos from(List<ScoreHistory> scoreHistories) {
    return new UserScoreInfos(scoreHistories.stream()
        .map(UserScoreInfo::from)
        .collect(Collectors.toList()));
  }

  public static HashMap<Long, Long> toSum(UserScoreInfos userScoreInfos) {
    HashMap<Long, Long> ret = new HashMap<>();
    for (UserScoreInfo info :userScoreInfos){
      ret.merge(info.getUserId(), info.getScore(), Long::sum);
    }
    return ret;
  }
}