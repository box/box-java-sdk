package com.box.sdk;

/**
 * Global settings to apply to all API requests.
 */
public final class BoxGlobalSettings {
    private static int connectTimeout = 0;
    private static int readTimeout = 0;
    private static int maxRetryAttempts = BoxAPIConnection.DEFAULT_MAX_RETRIES;
    private static boolean useZstdCompression = true;

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

    /*
     * Returns the global settings for using Zstd compression.
     * @return true if Zstd compression is enabled, false otherwise
     */
    public static boolean getUseZstdCompression() {
        return useZstdCompression;
    }

    /*
     * Sets the global settings for using Zstd compression.
     * @param useZstdCompression true to enable Zstd compression, false otherwise
     */
    public static void setUseZstdCompression(boolean useZstdCompression) {
        BoxGlobalSettings.useZstdCompression = useZstdCompression;
    }
}
