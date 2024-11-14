package com.toyproject.hyeonworld.api.sse.interfaces;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 14.
 */
public class CustomEmitters {
    protected final List<CustomEmitter> emitters;

    protected CustomEmitters() {
        this.emitters = new ArrayList<>();
    }

    public void add(long userId) {
        emitters.add(CustomEmitter.from(userId));
    }

    public void remove(long userId) {
        emitters.removeIf(emitter -> emitter.getId() == userId);
    }
}
