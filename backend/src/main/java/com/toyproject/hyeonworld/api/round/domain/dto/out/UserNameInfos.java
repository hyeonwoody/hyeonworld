package com.toyproject.hyeonworld.api.round.domain.dto.out;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 12.
 */
public record UserNameInfos(
        List<UserNameInfo> userNameInfos
) {
    public UserNameInfos(List<UserNameInfo> userNameInfos) {
        this.userNameInfos = userNameInfos;
    }

    public static UserNameInfos from(Map<Long, String> winnersIdName) {
        List<UserNameInfo> userNameInfos = winnersIdName.entrySet().stream()
                .map(entry -> UserNameInfo.from(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return new UserNameInfos(userNameInfos);
    }
}
