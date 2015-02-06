package com.box.sdk;

/**
 * Listener to listen to Box API connection events.
 */
public interface BoxAPIConnectionListener {

    /**
     * Called when the Box API connection refreshes its tokens.
     */
    void onRefresh();
}
