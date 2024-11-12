package com.toyproject.hyeonworld.api.user.controller.dto.res;

import com.toyproject.hyeonworld.api.user.domain.dto.out.UserWaitingListDto;
import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
public record UserWaitingListResponse (List<String> names){

  public static UserWaitingListResponse from(List<String> waitingNameList) {
    return new UserWaitingListResponse(waitingNameList);
  }
}
