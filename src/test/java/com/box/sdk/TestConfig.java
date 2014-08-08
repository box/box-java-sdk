package com.box.sdk;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

final class TestConfig {
    private static String authToken = null;

    private TestConfig() { }

    public static String getAuthToken() {
        if (authToken == null || authToken.equals("")) {
            Properties configProperties = new Properties();
            InputStream input = null;

            try {
                input = new FileInputStream("src/test/config/config.properties");
                configProperties.load(input);
            } catch (IOException e) {
                throw new IllegalStateException("An authToken property wasn't set in "
                    + "src/test/config/config.properties.", e);
            }

            authToken = configProperties.getProperty("authToken");
            if (authToken.equals("")) {
                throw new IllegalStateException("An authToken property wasn't set in "
                    + "src/test/config/config.properties.");
            }
        }

        return authToken;
    }
}
