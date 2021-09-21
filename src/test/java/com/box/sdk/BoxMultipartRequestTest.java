package com.box.sdk;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.junit.Test;


public class BoxMultipartRequestTest {

    @Test
    public void testFieldsComeBeforeFile() throws Exception {
        // Setup and Expectations
        HttpURLConnection mockConnection = mock(HttpURLConnection.class);
        ByteArrayOutputStream bodyOutputStream = new ByteArrayOutputStream();
        when(mockConnection.getOutputStream()).thenReturn(bodyOutputStream);
        String fileContents = "test body";

        String expectedBody =
            "--da39a3ee5e6b4b0d3255bfef95601890afd80709\r\n"
                + "Content-Disposition: form-data; name=\"testField\"\r\n"
                + "\r\n"
                + "testValue\r\n"
                + "--da39a3ee5e6b4b0d3255bfef95601890afd80709\r\n"
                + "Content-Disposition: form-data; name=\"file\"; filename=\"testfile\"\r\n"
                + "Content-Type: application/octet-stream\r\n"
                + "\r\n"
                + "test body\r\n"
                + "--da39a3ee5e6b4b0d3255bfef95601890afd80709--";

        // Execute
        BoxAPIConnection api = new BoxAPIConnection("");
        BoxMultipartRequest request = new TestBoxMultipartRequest(api, new URL("http://localhost"));
        request.putField("testField", "testValue");
        request.setFile(new ByteArrayInputStream(fileContents.getBytes()), "testfile");
        request.writeBody(mockConnection, null);

        // Validate
        String body = bodyOutputStream.toString();
        assertEquals(expectedBody, body);
    }

    @Test
    public void testFieldsUploadFileCallbackComeBeforeFile() throws Exception {
        // Setup and Expectations
        HttpURLConnection mockConnection = mock(HttpURLConnection.class);
        ByteArrayOutputStream bodyOutputStream = new ByteArrayOutputStream();
        when(mockConnection.getOutputStream()).thenReturn(bodyOutputStream);
        final String fileContents = "test body";

        final String expectedBody =
            "--da39a3ee5e6b4b0d3255bfef95601890afd80709\r\n"
                + "Content-Disposition: form-data; name=\"testField\"\r\n"
                + "\r\n"
                + "testValue\r\n"
                + "--da39a3ee5e6b4b0d3255bfef95601890afd80709\r\n"
                + "Content-Disposition: form-data; name=\"file\"; filename=\"testfile\"\r\n"
                + "Content-Type: application/octet-stream\r\n"
                + "\r\n"
                + "test body\r\n"
                + "--da39a3ee5e6b4b0d3255bfef95601890afd80709--";

        // Execute
        BoxAPIConnection api = new BoxAPIConnection("");
        BoxMultipartRequest request = new TestBoxMultipartRequest(api, new URL("http://localhost"));
        request.putField("testField", "testValue");
        request.setUploadFileCallback(new UploadFileCallback() {
            @Override
            public void writeToStream(OutputStream outputStream) throws IOException {
                outputStream.write(fileContents.getBytes());
            }
        }, "testfile");
        request.writeBody(mockConnection, null);

        // Validate
        String body = bodyOutputStream.toString();
        assertEquals(expectedBody, body);
    }

    private final class TestBoxMultipartRequest extends BoxMultipartRequest {
        private TestBoxMultipartRequest(BoxAPIConnection api, URL url) {
            super(api, url);
        }

        public void testWriteBody(HttpURLConnection connection, ProgressListener listener) {
            this.writeBody(connection, listener);
        }
    }
}
