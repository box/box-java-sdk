package com.box.sdk;

import java.io.IOException;
import java.io.InputStream;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

/** Utility class to write body from stream to BufferedSink used by OkHttp. */
final class RequestBodyFromStream extends RequestBody {
  private final InputStream inputStream;
  private final ProgressListener progressListener;
  private final MediaType mediaType;
  private final long fileSize;

  RequestBodyFromStream(
      InputStream inputStream,
      MediaType mediaType,
      ProgressListener progressListener,
      long fileSize) {
    this.inputStream = inputStream;
    this.progressListener = progressListener;
    this.mediaType = mediaType;
    this.fileSize = fileSize;
  }

  @Override
  public MediaType contentType() {
    return mediaType;
  }

  @Override
  public void writeTo(BufferedSink bufferedSink) {
    if (progressListener == null) {
      writeWithoutProgressListener(bufferedSink);
    } else {
      writeWithProgressListener(bufferedSink);
    }
  }

  /**
   * Returns content length. If the content length cannot be determined (is coming from a stream)
   * this value will be -1.
   *
   * @return Long representing content length
   */
  @Override
  public long contentLength() {
    return fileSize;
  }

  private void writeWithoutProgressListener(BufferedSink bufferedSink) {
    try (Source source = Okio.source(this.inputStream)) {
      bufferedSink.writeAll(source);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void writeWithProgressListener(BufferedSink bufferedSink) {
    try {
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
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        this.inputStream.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
