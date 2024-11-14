package com.toyproject.hyeonworld.api.sse.constant;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 14.
 */
public enum EmitterType {
    WAITING_LIST("WaitingList"),
    ALL("All");

    private String eventName;

    EmitterType(String EventName) {
    }
}
