package com.box.sdk;

/**
 * Contains all the default configurations for the Box SDK that can be modified by the user.
 */
public class BoxSDKConfiguration {

    // Defaults here.
    private static final String DEFAULT_USER_AGENT = "Box Java SDK v0.4";

    // User settings.
    private static String userAgent = DEFAULT_USER_AGENT;

    /**
     * Returns the user agent header.
     * @return the user agent header.
     */
    public static String getUserAgent() {
        return userAgent;
    }

    /**
     * Overrides the default user agent with the specified {@code userAgent}.
     * @param userAgent the user agent to send with API requests.
     */
    public static void setUserAgent(String userAgent) {
        BoxSDKConfiguration.userAgent = userAgent;
    }
}
