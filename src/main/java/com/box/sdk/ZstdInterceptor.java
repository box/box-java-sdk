package com.box.sdk;

import com.github.luben.zstd.ZstdInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

/**
 * Interceptor that adds zstd compression support to API requests.
 * This interceptor adds zstd to the Accept-Encoding header and handles decompression of zstd responses.
 */
public class ZstdInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        // Add zstd to the Accept-Encoding header
        String acceptEncoding;
        String acceptEncodingHeader = request.header("Accept-Encoding");
        if (acceptEncodingHeader == null) {
            acceptEncoding = "zstd";
        } else {
            acceptEncoding = acceptEncodingHeader + ", zstd";
        }

        Request compressedRequest = request.newBuilder()
            .removeHeader("Accept-Encoding")
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
            decompressedBytes,
            originalBody.contentType()
        );

        return response.newBuilder()
            .body(decompressedBody)
            .addHeader("X-Content-Encoding", contentEncoding)
            .removeHeader("Content-Encoding")
            .removeHeader("Content-Length")
            .build();
    }
}
