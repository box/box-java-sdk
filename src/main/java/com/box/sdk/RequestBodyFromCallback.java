package com.box.sdk;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 *
 */
final class RequestBodyFromCallback extends RequestBody {

    private final UploadFileCallback callback;
    private final MediaType mediaType;

    RequestBodyFromCallback(UploadFileCallback callback, MediaType mediaType) {
        this.callback = callback;
        this.mediaType = mediaType;
    }

    @Override
    public MediaType contentType() {
        return mediaType;
    }

    @Override
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        callback.writeToStream(bufferedSink.outputStream());
    }
}
