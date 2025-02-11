package com.box.sdk;

import com.github.luben.zstd.ZstdInputStream;
import java.util.List;
import okhttp3.*;
import java.io.ByteArrayOutputStream;

import java.io.IOException;

/**
 * Interceptor that adds zstd compression support to API requests.
 * This interceptor adds zstd to the Accept-Encoding header and handles decompression of zstd responses.
 */
public class ZstdInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        // Add zstd to the Accept-Encoding header
        String acceptEncoding;
        List<String> acceptEncodingHeaders = request.headers("Accept-Encoding");
        if (acceptEncodingHeaders.isEmpty()) {
            acceptEncoding = "zstd";
        } else {
            acceptEncoding = acceptEncodingHeaders.get(0) + ", zstd";
        }

        Request compressedRequest = request.newBuilder()
            .addHeader("Accept-Encoding", acceptEncoding)
            .build();

        Response response = chain.proceed(compressedRequest);
        String contentEncoding = response.header("Content-Encoding");

        // Only handle zstd encoded responses, let OkHttp handle gzip and others
        if (contentEncoding == null || !contentEncoding.equalsIgnoreCase("zstd")) {
            return response;
        }

        ResponseBody originalBody = response.body();
        if (originalBody == null) {
            return response;
        }

        // Buffer the entire response body
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (ZstdInputStream zstdStream = new ZstdInputStream(originalBody.byteStream())) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = zstdStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
        byte[] decompressedBytes = outputStream.toByteArray();

        // Create a new response body that serves the buffered content
        ResponseBody decompressedBody = ResponseBody.create(
            originalBody.contentType(),
            decompressedBytes
        );

        return response.newBuilder()
            .body(decompressedBody)
            .removeHeader("Content-Encoding")
            .removeHeader("Content-Length")
            .build();
    }
}
