package com.toyproject.hyeonworld.api.user.domain.dto.out;

import com.toyproject.hyeonworld.api.user.infrastructure.entity.User;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 12.
 */
public class NameInGameInfos extends ArrayList<NameInGameInfo> {
    private List<NameInGameInfo> nameInGames;

    public NameInGameInfos(List<User> users) {
        this.nameInGames = new ArrayList<>();
        users.stream()
                .peek(user -> this.nameInGames.add(NameInGameInfo.from(user.getName(), user.getInGame())));

    }

    public static NameInGameInfos from(List<User> users) {
        return new NameInGameInfos(users);
    }

    public List<String> getWatingNameList() {
        return this.stream()
                .filter(NameInGameInfo::inGame)
                .map(NameInGameInfo::name)
                .toList();
    }
}
