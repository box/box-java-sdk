package com.box.sdk;

/**
 *
 */
public interface BoxAPIResponseEvent {
    /**
     * Called on success
     */
    void onSuccess();

    /**
     * Called on failure
     */
    void onFailure();
}
