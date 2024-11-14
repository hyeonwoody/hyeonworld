package com.toyproject.hyeonworld.api.session.event;


/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 14.
 */
public record LogOutSessionEvent(byte relationType, long userId, String userName) implements SessionEvent {

    public static LogOutSessionEvent from(Byte relationType, Long userId, String name) {
        return new LogOutSessionEvent(relationType, userId, name);
    }
}