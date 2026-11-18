package com.box.sdkgen.networking.inputstreamrequestbody;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import okhttp3.MediaType;
import okio.Buffer;
import org.junit.jupiter.api.Test;

public class InputStreamRequestBodyTest {

  private static final MediaType CONTENT_TYPE = MediaType.parse("application/octet-stream");
  private static final byte[] CONTENT = "0123456789abcdef".getBytes(StandardCharsets.UTF_8);
  private static final int INITIAL_POSITION = 4;

  @Test
  public void testByteArrayStreamIsSentAgainFromItsInitialPosition() throws IOException {
    ByteArrayInputStream stream = new ByteArrayInputStream(CONTENT);
    stream.skip(INITIAL_POSITION);
    InputStreamRequestBody body = new InputStreamRequestBody(stream, CONTENT_TYPE);
    byte[] expected = Arrays.copyOfRange(CONTENT, INITIAL_POSITION, CONTENT.length);

    assertArrayEquals(expected, write(body));
    assertTrue(body.canBeResent());
    assertArrayEquals(expected, write(body));
  }

  @Test
  public void testFileStreamIsSentAgainFromItsInitialPosition() throws IOException {
    File file = createTempFile();
    try (FileInputStream stream = new FileInputStream(file)) {
      stream.skip(INITIAL_POSITION);
      InputStreamRequestBody body = new InputStreamRequestBody(stream, CONTENT_TYPE);
      byte[] expected = Arrays.copyOfRange(CONTENT, INITIAL_POSITION, CONTENT.length);

      assertArrayEquals(expected, write(body));
      assertTrue(body.canBeResent());
      assertArrayEquals(expected, write(body));
    }
  }

  @Test
  public void testStreamThatCannotBeRewoundIsSentOnlyOnce() throws IOException {
    InputStream stream = new BufferedInputStream(new ByteArrayInputStream(CONTENT));
    InputStreamRequestBody body = new InputStreamRequestBody(stream, CONTENT_TYPE);

    assertTrue(body.canBeResent());
    assertArrayEquals(CONTENT, write(body));
    assertFalse(body.canBeResent());
    assertThrows(IOException.class, () -> write(body));
  }

  @Test
  public void testStreamIsNotClosedAfterBeingSent() throws IOException {
    CloseTrackingInputStream stream =
        new CloseTrackingInputStream(new ByteArrayInputStream(CONTENT));
    InputStreamRequestBody body = new InputStreamRequestBody(stream, CONTENT_TYPE);

    write(body);

    assertFalse(stream.closed);
  }

  private static byte[] write(InputStreamRequestBody body) throws IOException {
    Buffer buffer = new Buffer();
    body.writeTo(buffer);
    return buffer.readByteArray();
  }

  private static File createTempFile() throws IOException {
    File file = Files.createTempFile("input-stream-request-body", ".bin").toFile();
    file.deleteOnExit();
    Files.write(file.toPath(), CONTENT);
    return file;
  }

  private static class CloseTrackingInputStream extends FilterInputStream {
    private boolean closed;

    CloseTrackingInputStream(InputStream in) {
      super(in);
    }

    @Override
    public void close() throws IOException {
      closed = true;
      super.close();
    }
  }
}
