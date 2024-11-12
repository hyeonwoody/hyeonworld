package com.toyproject.hyeonworld.api.user.domain.dto.out;

import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */

record NameInGameInfo (
        String name,
        boolean inGame
){
    NameInGameInfo(String name, boolean inGame) {
        this.name = name;
        this.inGame = inGame;
    }

    public static NameInGameInfo from (String name, boolean inGame) {
        return new NameInGameInfo(name, inGame);
    }

}
