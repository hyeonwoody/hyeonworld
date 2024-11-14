package com.toyproject.hyeonworld.api.sse.constant;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 14.
 */
public enum EventType {
    ADD_NAME_TO_WAITING_LIST ("AddNameToWaitingList"),
    REMOVE_NAME_FROM_WAITING_LIST ("RemoveNameFromWaitingList");

    private String eventName;

    EventType(String EventName) {
    }
}
