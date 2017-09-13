package com.box.sdk;

/**
 * Global settings to apply to all API requests.
 */
public final class BoxGlobalSettings {
    private static int connectTimeout = 0;
    private static int readTimeout = 0;

    private BoxGlobalSettings() {
    }

    /**
     * Returns the current global connect timeout.
     * @return connect timeout
     */
    public static int getConnectTimeout() {
        return connectTimeout;
    }

    /**
     * Sets the global connect timeout.
     * @param connectTimeout timeout in milliseconds
     */
    public static void setConnectTimeout(int connectTimeout) {
        BoxGlobalSettings.connectTimeout = connectTimeout;
    }

    /**
     * Returns the current global read timeout.
     * @return read timeout
     */
    public static int getReadTimeout() {
        return readTimeout;
    }

    /**
     * Sets the global read timeout.
     * @param readTimeout timeout in milliseconds
     */
    public static void setReadTimeout(int readTimeout) {
        BoxGlobalSettings.readTimeout = readTimeout;
    }
}
