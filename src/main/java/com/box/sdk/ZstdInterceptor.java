package com.box.sdk;

import com.github.luben.zstd.ZstdInputStream;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;
import org.jetbrains.annotations.NotNull;

/**
 * Interceptor that adds zstd compression support to API requests. This interceptor adds zstd to the
 * Accept-Encoding header and handles decompression of zstd responses.
 */
public class ZstdInterceptor implements Interceptor {
  @NotNull
  @Override
  public Response intercept(Chain chain) throws IOException {
    Request request = chain.request();

    // Add zstd to the Accept-Encoding header
    String acceptEncoding;
    String acceptEncodingHeader = request.header("Accept-Encoding");
    if (acceptEncodingHeader == null || acceptEncodingHeader.isEmpty()) {
      acceptEncoding = "zstd";
    } else {
      acceptEncoding = acceptEncodingHeader + ", zstd";
    }

    Request compressedRequest =
        request
            .newBuilder()
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

    // Create a streaming response body
    ResponseBody decompressedBody = createStreamingResponseBody(originalBody);

    return response
        .newBuilder()
        .body(decompressedBody)
        .addHeader("X-Content-Encoding", "zstd")
        .removeHeader("Content-Encoding")
        .removeHeader("Content-Length")
        .build();
  }

  /** Wraps the original response body in a streaming Zstd decompressor. */
  private ResponseBody createStreamingResponseBody(ResponseBody originalBody) {
    return new ResponseBody() {
      @Override
      public MediaType contentType() {
        return originalBody.contentType();
      }

      @Override
      public long contentLength() {
        return -1;
      }

      @Override
      public BufferedSource source() {
        InputStream decompressedStream;
        try {
          decompressedStream = new ZstdInputStream(originalBody.byteStream());
        } catch (IOException e) {
          throw new RuntimeException("Failed to create ZstdInputStream", e);
        }

        Source source = Okio.source(decompressedStream);
        return Okio.buffer(source);
      }
    };
  }
}
