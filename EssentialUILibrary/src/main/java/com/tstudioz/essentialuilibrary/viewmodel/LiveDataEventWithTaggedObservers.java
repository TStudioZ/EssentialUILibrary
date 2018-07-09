package com.tstudioz.essentialuilibrary.viewmodel;

import java.util.HashMap;
import java.util.Map;

public class LiveDataEventWithTaggedObservers<T> {

    private final T content;

    private final Map<String, Boolean> handledMap = new HashMap<>();

    public LiveDataEventWithTaggedObservers(T content) {
        this.content = content;
    }

    private void putIfNotPresent(String tag) {
        if (!handledMap.containsKey(tag)) {
            handledMap.put(tag, false);
        }
    }

    private boolean isHandled(String tag) {
        return handledMap.get(tag);
    }

    private void setHandled(String tag) {
        handledMap.put(tag, true);
    }

    /**
     * Returns the content and prevents its use again.
     */
    public T getContentIfNotHandled(String tag) {
        putIfNotPresent(tag);
        if (isHandled(tag)) {
            return null;
        } else {
            setHandled(tag);
            return content;
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    public T peekContent() {
        return content;
    }

    /**
     * Returns the content if it's not already been handled.
     */
    public T peekContentIfNotHandled(String tag) {
        putIfNotPresent(tag);
        if (isHandled(tag))
            return null;
        return content;
    }
}
