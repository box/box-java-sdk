package com.box.sdk;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import okhttp3.Headers;
import org.jetbrains.annotations.NotNull;

final class BoxSensitiveDataSanitizer {
    private static final Set<String> SENSITIVE_KEYS = new HashSet<>(Arrays.asList("authorization", "access_token",
        "refresh_token", "subject_token", "token", "client_id", "client_secret", "code", "shared_link", "download_url",
        "jwt_private_key", "jwt_private_key_passphrase", "password"));

    private BoxSensitiveDataSanitizer() {
    }

    @NotNull
    public static Headers sanitizeHeaders(Headers originalHeaders) {
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

    private static boolean isSensitiveKey(@NotNull String key) {
        return SENSITIVE_KEYS.contains(key.toLowerCase());
    }
}
