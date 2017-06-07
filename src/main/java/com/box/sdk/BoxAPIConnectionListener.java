package com.box.sdk;

/**
 * Listener to listen to Box API connection events.
 */
public interface BoxAPIConnectionListener {

    /**
     * Called before the Box API connection refreshes its tokens.
     * It can short circuit the refresh if the listener returns false.
     * This is useful for testing the availability of a persistence resource such as a database.
     *
     * <p>The provided connection is guaranteed to not be auto-refreshed or modified by another listener until this
     * method returns.</p>
     *
     * @param api the API connection that was refreshed.
     * @return a boolean indicating whether the refresh should proceed
     */
    default boolean preRefresh(BoxAPIConnection api) { return true; };

    /**
     * Called when the Box API connection refreshes its tokens.
     *
     * <p>The provided connection is guaranteed to not be auto-refreshed or modified by another listener until this
     * method returns.</p>
     *
     * @param api the API connection that was refreshed.
     */
    void onRefresh(BoxAPIConnection api);

    /**
     * Called when an error occurs while attempting to refresh the Box API connection's tokens.
     *
     * <p>The provided connection is guaranteed to not be auto-refreshed or modified by another listener until this
     * method returns.</p>
     *
     * @param api   the API connection that encountered an error.
     * @param error the error that occurred.
     */
    void onError(BoxAPIConnection api, BoxAPIException error);
}
