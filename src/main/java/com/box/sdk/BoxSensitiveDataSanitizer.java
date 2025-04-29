package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import okhttp3.Headers;
import org.jetbrains.annotations.NotNull;

/**
 * Class used to sanitize sensitive data from payload.
 */
public final class BoxSensitiveDataSanitizer {
    private static final Set<String> SENSITIVE_KEYS = new HashSet<>(Arrays.asList("authorization", "access_token",
        "refresh_token", "subject_token", "token", "client_id", "client_secret", "code", "shared_link", "download_url",
        "jwt_private_key", "jwt_private_key_passphrase", "password"));

    private BoxSensitiveDataSanitizer() {
    }

    /**
     * Add key that should be sanitized
     *
     * @param key key to be sanitized
     */
    public static void addKeyToSanitize(String key) {
        SENSITIVE_KEYS.add(key);
    }

    @NotNull
    static Headers sanitizeHeaders(Headers originalHeaders) {
        Headers.Builder sanitizedHeadersBuilder = originalHeaders.newBuilder();

        for (String originalHeaderName : originalHeaders.names()) {
            if (isSensitiveKey(originalHeaderName)) {
                sanitizedHeadersBuilder.set(originalHeaderName, "[REDACTED]");
            } else {
                String headerValue = originalHeaders.get(originalHeaderName);
                if (headerValue != null) {
                    sanitizedHeadersBuilder.set(originalHeaderName, headerValue);
                }
            }
        }

        return sanitizedHeadersBuilder.build();
    }

    /**
     * Sanitize the json body. Only for the first level of the json.
     *
     * @param originalBody the original json body
     * @return the sanitized json body
     */
    @NotNull
    static JsonObject sanitizeJsonBody(JsonObject originalBody) {
        JsonObject sanitizedBody = new JsonObject();

        for (String key : originalBody.names()) {
            if (isSensitiveKey(key)) {
                sanitizedBody.set(key, "[REDACATED]");
            } else {
                sanitizedBody.set(key, originalBody.get(key));
            }
        }
        return sanitizedBody;
    }

    private static boolean isSensitiveKey(@NotNull String key) {
        return SENSITIVE_KEYS.contains(key.toLowerCase(java.util.Locale.ROOT));
    }
}
