package com.box.sdk;

import okhttp3.Headers;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

class BoxSensitiveDataSanitizer {
	private static final Set<String> sensitiveKeys = new HashSet<>(Arrays.asList(
		"authorization",
		"access_token",
		"refresh_token",
		"subject_token",
		"token",
		"client_id",
		"client_secret",
		"code",
		"shared_link",
		"download_url",
		"jwt_private_key",
		"jwt_private_key_passphrase",
		"password"
	));

	public static Headers sanitizeHeaders(Headers originalHeaders) {
		Headers.Builder sanitizedHeadersBuilder = originalHeaders.newBuilder();

		for (String originalHeaderName : originalHeaders.names()) {
			if (isSensitiveKey(originalHeaderName)) {
				sanitizedHeadersBuilder.set(originalHeaderName, "[REDACTED]");
			} else {
				String headerValue = originalHeaders.get(originalHeaderName);
				if(headerValue != null) {
					sanitizedHeadersBuilder.set(originalHeaderName, headerValue);
				}
			}
		}

		return sanitizedHeadersBuilder.build();
	}

	private static boolean isSensitiveKey(String key) {
		return sensitiveKeys.contains(key.toLowerCase());
	}
}
