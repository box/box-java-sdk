package com.box.sdk;

import static java.lang.String.format;

import java.util.Map;
import java.util.Optional;

final class BoxApiProvider {

    private static final String JWT_CONFIG_ENV_NAME = "JAVA_JWT_CONFIG";

    private BoxApiProvider() {
        // hiding constructor
    }

    static BoxDeveloperEditionAPIConnection jwtApiForServiceAccount() {
        Map<String, String> environmentProperties = System.getenv();
        String jwtConfigEncoded = getEnvProperty(environmentProperties, JWT_CONFIG_ENV_NAME);
        byte[] decodedJwtConfig = Base64.decode(jwtConfigEncoded);
        BoxConfig boxConfig = BoxConfig.readFrom(new String(decodedJwtConfig));
        InMemoryLRUAccessTokenCache tokenCache = new InMemoryLRUAccessTokenCache(10);
        return BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(boxConfig, tokenCache);
    }

    private static String getEnvProperty(Map<String, String> environmentProperties, String name) {
        return Optional.ofNullable(environmentProperties.get(name))
            .orElseThrow(() -> new RuntimeException(format("Missing '%s' environmental variable", name)));
    }
}
