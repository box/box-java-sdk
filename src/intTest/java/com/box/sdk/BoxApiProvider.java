package com.box.sdk;

import static java.lang.String.format;

import java.util.Map;
import java.util.Optional;

final class BoxApiProvider {

    private static final String CLIENT_ID_ENV_NAME = "CLIENT_ID";
    private static final String CLIENT_SECRET_ENV_NAME = "CLIENT_SECRET";
    private static final String ENTERPRISE_ID_ENV_NAME = "ENTERPRISE_ID";

    private BoxApiProvider() {
        // hiding constructor
    }

    static BoxAPIConnection ccgApiForServiceAccount() {
        Map<String, String> environmentProperties = System.getenv();
        String clientId = getEnvProperty(environmentProperties, CLIENT_ID_ENV_NAME);
        String clientSecret = getEnvProperty(environmentProperties, CLIENT_SECRET_ENV_NAME);
        String enterpriseId = getEnvProperty(environmentProperties, ENTERPRISE_ID_ENV_NAME);
        return BoxCCGAPIConnection.applicationServiceAccountConnection(clientId, clientSecret, enterpriseId);
    }

    private static String getEnvProperty(Map<String, String> environmentProperties, String name) {
        return Optional.ofNullable(environmentProperties.get(name))
            .orElseThrow(() -> new RuntimeException(format("Missing '%s' environmental variable", name)));
    }
}
