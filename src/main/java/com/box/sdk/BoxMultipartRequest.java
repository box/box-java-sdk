package com.box.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BoxMultipartRequest extends BoxAPIRequest {
    private static final Logger LOGGER = Logger.getLogger(BoxFolder.class.getName());
    private static final String BOUNDARY = "da39a3ee5e6b4b0d3255bfef95601890afd80709";
    private static final int BUFFER_SIZE = 8192;

    private final StringBuilder loggedRequest = new StringBuilder();

    private OutputStream outputStream;
    private InputStream inputStream;
    private String filename;
    private long fileSize;
    private Map<String, String> fields;
    private boolean firstBoundary;

    public BoxMultipartRequest(BoxAPIConnection api, URL url) {
        super(api, url, "POST");

        this.fields = new HashMap<String, String>();
        this.firstBoundary = true;

        this.addHeader("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
    }

    public void putField(String key, String value) {
        this.fields.put(key, value);
    }

    public void putField(String key, Date value) {
        this.fields.put(key, BoxDateFormat.format(value));
    }

    public void setFile(InputStream inputStream, String filename) {
        this.inputStream = inputStream;
        this.filename = filename;
    }

    public void setFile(InputStream inputStream, String filename, long fileSize) {
        this.setFile(inputStream, filename);
        this.fileSize = fileSize;
    }

    @Override
    public void setBody(InputStream stream) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setBody(String body) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeBody(HttpURLConnection connection, ProgressListener listener) {
        try {
            connection.setChunkedStreamingMode(0);
            connection.setDoOutput(true);
            this.outputStream = connection.getOutputStream();

            this.writePartHeader(new String[][] {{"name", "filename"}, {"filename", this.filename}},
                "application/octet-stream");

            OutputStream fileContentsOutputStream = this.outputStream;
            if (listener != null) {
                fileContentsOutputStream = new ProgressOutputStream(this.outputStream, listener, this.fileSize);
            }
            byte[] buffer = new byte[BUFFER_SIZE];
            int n = this.inputStream.read(buffer);
            while (n != -1) {
                fileContentsOutputStream.write(buffer, 0, n);
                n = this.inputStream.read(buffer);
            }

            if (LOGGER.isLoggable(Level.FINE)) {
                this.loggedRequest.append("<File Contents Omitted>");
            }

            for (Map.Entry<String, String> entry : this.fields.entrySet()) {
                this.writePartHeader(new String[][] {{"name", entry.getKey()}});
                this.writeOutput(entry.getValue());
            }

            this.writeBoundary();
        } catch (IOException e) {
            throw new BoxAPIException("Couldn't connect to the Box API due to a network error.", e);
        }
    }

    @Override
    protected void resetBody() throws IOException {
        this.firstBoundary = true;
        this.inputStream.reset();
        this.loggedRequest.setLength(0);
    }

    @Override
    protected String bodyToString() {
        return this.loggedRequest.toString();
    }

    private void writeBoundary() throws IOException {
        if (!this.firstBoundary) {
            this.writeOutput("\r\n");
        }

        this.firstBoundary = false;
        this.writeOutput("--");
        this.writeOutput(BOUNDARY);
    }

    private void writePartHeader(String[][] formData) throws IOException {
        this.writePartHeader(formData, null);
    }

    private void writePartHeader(String[][] formData, String contentType) throws IOException {
        this.writeBoundary();
        this.writeOutput("\r\n");
        this.writeOutput("Content-Disposition: form-data");
        for (int i = 0; i < formData.length; i++) {
            this.writeOutput("; ");
            this.writeOutput(formData[i][0]);
            this.writeOutput("=\"");
            this.writeOutput(formData[i][1]);
            this.writeOutput("\"");
        }

        if (contentType != null) {
            this.writeOutput("\r\nContent-Type: ");
            this.writeOutput(contentType);
        }

        this.writeOutput("\r\n\r\n");
    }

    private void writeOutput(String s) throws IOException {
        this.outputStream.write(s.getBytes(StandardCharsets.UTF_8));
        if (LOGGER.isLoggable(Level.FINE)) {
            this.loggedRequest.append(s);
        }
    }

    private void writeOutput(int b) throws IOException {
        this.outputStream.write(b);
    }
}
