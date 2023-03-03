package com.box.sdk;

import java.io.IOException;
import java.io.InputStream;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * Utility class to write body from stream to BufferedSink used by OkHttp.
 */
final class RequestBodyFromStream extends RequestBody {
    private final InputStream inputStream;
    private final ProgressListener progressListener;
    private final MediaType mediaType;
    private final int contentLength;

    RequestBodyFromStream(InputStream inputStream, MediaType mediaType, ProgressListener progressListener) {
        this.inputStream = inputStream;
        this.progressListener = progressListener;
        this.mediaType = mediaType;
        try {
            this.contentLength = inputStream.available();
        } catch (IOException e) {
            throw new RuntimeException("Cannot read input stream for upload", e);
        }
    }

    @Override
    public long contentLength() {
        return contentLength;
    }

    @Override
    public MediaType contentType() {
        return mediaType;
    }

    @Override
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        byte[] buffer = new byte[AbstractBoxMultipartRequest.BUFFER_SIZE];
        int n = this.inputStream.read(buffer);
        int totalWritten = 0;
        while (n != -1) {
            bufferedSink.write(buffer, 0, n);
            totalWritten += n;
            if (progressListener != null) {
                progressListener.onProgressChanged(totalWritten, this.contentLength());
            }
            n = this.inputStream.read(buffer);
        }
    }
}
