package com.toyproject.hyeonworld.api.sse.interfaces;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 14.
 */
public interface EmitterManager {

    void add(long userId);

    void remove(long userId);
}
