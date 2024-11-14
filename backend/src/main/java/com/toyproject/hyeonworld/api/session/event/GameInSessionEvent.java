package com.toyproject.hyeonworld.api.session.event;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 14.
 */
public record GameInSessionEvent(byte relationType, long userId, String userName) implements SessionEvent {

    public static GameInSessionEvent from(Byte relationType, Long userId, String userName) {
        return new GameInSessionEvent(relationType, userId, userName);
    }
}
