package com.box.sdkgen.networking.inputstreamrequestbody;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Okio;

/**
 * A request body that streams a caller-provided {@link InputStream} without buffering its content.
 *
 * <p>The body does not close the stream; the network client closes it once the request and all its
 * retries finish. The body can be sent again on a retry only if the stream can be rewound without
 * copying it: a {@link ByteArrayInputStream} via mark/reset, or a {@link FileInputStream} via its
 * channel position. Any other stream can be sent only once.
 */
public class InputStreamRequestBody extends RequestBody {

  private static final long NO_FILE_POSITION = -1;

  private final InputStream stream;
  private final MediaType contentType;
  private final long initialFilePosition;
  private boolean written;

  public InputStreamRequestBody(InputStream stream, MediaType contentType) {
    this.stream = stream;
    this.contentType = contentType;
    if (stream instanceof ByteArrayInputStream) {
      stream.mark(0);
    }
    this.initialFilePosition = readFilePosition(stream);
  }

  @Override
  public MediaType contentType() {
    return contentType;
  }

  @Override
  public void writeTo(BufferedSink sink) throws IOException {
    if (written) {
      rewind();
    }
    written = true;
    sink.writeAll(Okio.source(stream));
  }

  /**
   * Returns whether this body can be written again, either because it has not been written yet or
   * because its stream can be rewound.
   */
  public boolean canBeResent() {
    return !written || isRewindable();
  }

  private boolean isRewindable() {
    return stream instanceof ByteArrayInputStream || initialFilePosition != NO_FILE_POSITION;
  }

  private void rewind() throws IOException {
    if (stream instanceof ByteArrayInputStream) {
      stream.reset();
      return;
    }
    if (initialFilePosition != NO_FILE_POSITION) {
      ((FileInputStream) stream).getChannel().position(initialFilePosition);
      return;
    }
    throw new IOException("Request body stream was already sent and cannot be rewound");
  }

  private static long readFilePosition(InputStream stream) {
    if (!(stream instanceof FileInputStream)) {
      return NO_FILE_POSITION;
    }
    try {
      return ((FileInputStream) stream).getChannel().position();
    } catch (IOException e) {
      return NO_FILE_POSITION;
    }
  }
}
