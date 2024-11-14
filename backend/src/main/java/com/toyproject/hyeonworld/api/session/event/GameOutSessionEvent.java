package com.toyproject.hyeonworld.api.session.event;


/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 14.
 */
public record GameOutSessionEvent(byte relationType, long userId, String userName) implements SessionEvent {

    public static GameOutSessionEvent from(byte relationType, Long userId, String userName) {
        return new GameOutSessionEvent(relationType, userId, userName);
    }
}