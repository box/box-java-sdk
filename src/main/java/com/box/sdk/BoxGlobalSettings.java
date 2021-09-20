package com.box.sdk;

/**
 * Global settings to apply to all API requests.
 */
public final class BoxGlobalSettings {
    private static int connectTimeout = 0;
    private static int readTimeout = 0;
    private static int maxRetryAttempts = BoxAPIConnection.DEFAULT_MAX_RETRIES;

    private BoxGlobalSettings() {
    }

    /**
     * Returns the current global connect timeout.
     *
     * @return connect timeout
     */
    public static int getConnectTimeout() {
        return connectTimeout;
    }

    /**
     * Sets the global connect timeout.
     *
     * @param connectTimeout timeout in milliseconds
     */
    public static void setConnectTimeout(int connectTimeout) {
        BoxGlobalSettings.connectTimeout = connectTimeout;
    }

    /**
     * Returns the current global read timeout.
     *
     * @return read timeout
     */
    public static int getReadTimeout() {
        return readTimeout;
    }

    /**
     * Sets the global read timeout.
     *
     * @param readTimeout timeout in milliseconds
     */
    public static void setReadTimeout(int readTimeout) {
        BoxGlobalSettings.readTimeout = readTimeout;
    }

    /**
     * Returns the global total maximum number of times an API request will be tried when error responses
     * are received.
     *
     * @return max number of request attempts
     * @deprecated getMaxRetryAttempts is preferred because it more clearly gets the number
     * of times a request should be retried after an error response is received.
     */
    @Deprecated
    public static int getMaxRequestAttempts() {
        return maxRetryAttempts + 1;
    }

    /**
     * Sets the global total maximum number of times an API request will be tried when error responses
     * are received.
     *
     * @param attempts maximum number of request attempts
     * @deprecated setMaxRetryAttempts is preferred because it more clearly sets the number
     * of times a request should be retried after an error response is received.
     */
    @Deprecated
    public static void setMaxRequestAttempts(int attempts) {
        BoxGlobalSettings.maxRetryAttempts = attempts - 1;
    }

    /**
     * Returns the global maximum number of times an API request will be retried after an error response
     * is received.
     *
     * @return max number of request attempts
     */
    public static int getMaxRetryAttempts() {
        return maxRetryAttempts;
    }

    /**
     * Sets the global maximum number of times an API request will be retried after an error response
     * is received.
     *
     * @param attempts maximum number of request attempts
     */
    public static void setMaxRetryAttempts(int attempts) {
        BoxGlobalSettings.maxRetryAttempts = attempts;
    }
}
