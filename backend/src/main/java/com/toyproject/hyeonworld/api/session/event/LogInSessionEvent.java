package com.toyproject.hyeonworld.api.session.event;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 14.
 */
public record LogInSessionEvent(byte relationType, long userId, String userName) implements SessionEvent {

    public static LogInSessionEvent from(byte relationType, long userId, String userName) {
        return new LogInSessionEvent(relationType, userId, userName);
    }
}