package com.box.sdk;

import static com.box.sdk.BinaryBodyUtils.writeStreamWithContentLength;
import static com.box.sdk.StandardCharsets.UTF_8;
import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.junit.Test;

public class BoxAPIResponseTest {

    /**
     * Simulated large InputStream that generates data on demand.
     */
    static class LargeByteArrayInputStream extends InputStream {
        private final long size;
        private long bytesRead = 0;

        LargeByteArrayInputStream(long size) {
            this.size = size;
        }

        @Override
        public int read() throws IOException {
            if (bytesRead < size) {
                bytesRead++;
                return 'A'; // Simulated byte
            }
            return -1; // End of stream
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            if (bytesRead >= size) {
                return -1;
            }
            int bytesToRead = (int) Math.min(len, size - bytesRead);
            for (int i = 0; i < bytesToRead; i++) {
                b[off + i] = 'A';
            }
            bytesRead += bytesToRead;
            return bytesToRead;
        }
    }


    /**
     * Null OutputStream that discards written data (useful for counting bytes).
     */
    class NullOutputStream extends OutputStream {
        long bytesWritten = 0;
        @Override
        public void write(int b) {
            bytesWritten++;
        }

        @Override
        public void write(byte[] b, int off, int len) {
            bytesWritten += len;
        }

        long getBytesWritten() {
            return bytesWritten;
        }
    }

    @Test
    public void testAPIResponseHeaderIsCaseInsensitive() {
        Map<String, List<String>> headers = new TreeMap<>();
        headers.put("FOO", singletonList("bAr"));
        BoxAPIResponse response = new BoxAPIResponse(202, "GET", "https://aaa.com", headers);

        assertThat(response.getHeaders().get("foo"), contains("bAr"));
        assertThat(response.getHeaderField("foo"), is("bAr"));
        assertThat(response.getHeaders().get("fOo"), contains("bAr"));
        assertThat(response.getHeaderField("fOo"), is("bAr"));
        assertThat(response.getHeaders().get("FOO"), contains("bAr"));
        assertThat(response.getHeaderField("FOO"), is("bAr"));
    }

    @Test
    public void testWhenHeadersAreNull() {
        new BoxAPIResponse(202, "GET", "https://aaa.com", null);
    }

    @Test
    public void testReturnsFirstHeaderFromList() {
        Map<String, List<String>> headers = new TreeMap<>();
        headers.put("FOO", asList("bAr", "zab"));
        BoxAPIResponse response = new BoxAPIResponse(202, "GET", "https://aaa.com", headers);

        assertThat(response.getHeaders().get("foo"), contains("bAr", "zab"));
        assertThat(response.getHeaderField("foo"), is("bAr"));
    }

    @Test
    public void testResponseWithoutBodyToString() {
        Map<String, List<String>> headers = new TreeMap<>();
        headers.put("FOO", asList("bAr", "zab"));
        BoxAPIResponse response = new BoxAPIResponse(200, "GET", "https://aaa.com", headers);

        String str = response.toString();
        assertThat(str, is("Response\nGET https://aaa.com 200\nHeaders:\nfoo: [[bAr, zab]]"));
    }

    @Test
    public void testBinaryResponseToString() {
        Map<String, List<String>> headers = new TreeMap<>();
        headers.put("FOO", asList("bAr", "zab"));
        ByteArrayInputStream responseBody = new ByteArrayInputStream("This is image".getBytes(UTF_8));
        BoxAPIResponse response = new BoxAPIResponse(
            202, "GET", "https://aaa.com", headers, responseBody, "image/jpg", responseBody.available()
        );

        String str = response.toString();
        assertThat(str, is("Response\nGET https://aaa.com 202\nContent-Type: image/jpg\nHeaders:\nfoo: [[bAr, zab]]"));
    }

    @Test
    public void testJsonResponseToString() {
        Map<String, List<String>> headers = new TreeMap<>();
        headers.put("FOO", asList("bAr", "zab"));
        ByteArrayInputStream responseBody = new ByteArrayInputStream("{\"foo\":\"bar\"}".getBytes(UTF_8));
        BoxAPIResponse response = new BoxAPIResponse(
            202, "GET", "https://aaa.com", headers, responseBody, APPLICATION_JSON, responseBody.available()
        );

        String str = response.toString();
        assertThat(str, is(
            "Response\nGET https://aaa.com 202\n"
                + "Content-Type: application/json\n"
                + "Headers:\nfoo: [[bAr, zab]]\n"
                + "Body:\n{\"foo\":\"bar\"}")
        );
    }


    @Test
    public void testBoxJSONResponseToString() {
        Map<String, List<String>> headers = new TreeMap<>();
        headers.put("FOO", asList("bAr", "zab"));
        String responseBody = "{\"foo\":\"bar\"}";
        JsonObject jsonBody = Json.parse(responseBody).asObject();
        BoxAPIResponse response = new BoxJSONResponse(
            202, "GET", "https://aaa.com", headers, jsonBody
        );

        String str = response.toString();
        assertThat(str, is(
            "Response\nGET https://aaa.com 202\n"
                + "Content-Type: application/json\n"
                + "Headers:\nfoo: [[bAr, zab]]\n"
                + "Body:\n{\"foo\":\"bar\"}")
        );
    }

    @Test
    public void testLargeBinaryResponseContentLength() {
        long contentLength = Integer.MAX_VALUE + 10000L;
        Map<String, List<String>> headers = new TreeMap<>();
        headers.put("content-length", singletonList(Long.toString(contentLength)));
        LargeByteArrayInputStream inputStream = new LargeByteArrayInputStream(contentLength);
        NullOutputStream outputStream = new NullOutputStream();
        BoxAPIResponse response = new BoxAPIResponse(
            202, "GET", "https://aaa.com", headers, inputStream, "image/jpg", -1
        );
        writeStreamWithContentLength(response, outputStream);
        assertThat(outputStream.getBytesWritten(), is(contentLength));
    }
}
