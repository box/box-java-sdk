package com.box.sdk;

import java.io.IOException;
import java.io.InputStream;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

/**
 * Utility class to write body from stream to BufferedSink used by OkHttp.
 */
final class RequestBodyFromStream extends RequestBody {
    private final InputStream inputStream;
    private final ProgressListener progressListener;
    private final MediaType mediaType;

    RequestBodyFromStream(InputStream inputStream, MediaType mediaType, ProgressListener progressListener) {
        this.inputStream = inputStream;
        this.progressListener = progressListener;
        this.mediaType = mediaType;
    }

    @Override
    public long contentLength() {
        try {
            return inputStream.available();
        } catch (IOException e) {
            return 0;
        }
    }

    @Override
    public MediaType contentType() {
        return mediaType;
    }

    @Override
    public void writeTo(BufferedSink bufferedSink) {
        try (Source source = Okio.source(this.inputStream)) {
            bufferedSink.writeAll(source);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
