package com.tstudioz.essentialuilibrary.viewmodel;

public class LiveDataEvent<T> {

    private final T content;
    private boolean hasBeenHandled = false;

    public LiveDataEvent(T content) {
        this.content = content;
    }

    /**
     * Returns the content and prevents its use again.
     */
    public T getContentIfNotHandled() {
        if (hasBeenHandled) {
            return null;
        } else {
            hasBeenHandled = true;
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
    public T peekContentIfNotHandled() {
        if (hasBeenHandled)
            return null;
        return content;
    }

    /**
     * Mark the content as handled.
     */
    public void handleContent() {
        hasBeenHandled = true;
    }
}
