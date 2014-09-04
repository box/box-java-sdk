package com.box.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

    private final StringBuilder loggedRequest = new StringBuilder();
    private OutputStream outputStream;
    private InputStream inputStream;
    private String filename;
    private Map<String, String> fields;

    public BoxMultipartRequest(BoxAPIConnection api, URL url) {
        super(api, url, "POST");

        this.fields = new HashMap<String, String>();

        this.getConnection().addRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
        this.getConnection().setChunkedStreamingMode(0);
    }

    public void putField(String key, String value) {
        this.fields.put(key, value);
    }

    public void putField(String key, Date value) {
        this.fields.put(key, String.format("%FT%T-00:00", value));
    }

    public void setFile(InputStream inputStream, String filename) {
        this.inputStream = inputStream;
        this.filename = filename;
    }

    @Override
    public void writeBody() {
        try {
            this.getConnection().setDoOutput(true);
            this.outputStream = this.getConnection().getOutputStream();

            this.writePartHeader(new String[][] {{"name", "filename"}, {"filename", this.filename}},
                "application/octet-stream");
            int b = this.inputStream.read();
            while (b != -1) {
                this.writeOutput(b);
                b = this.inputStream.read();
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
    protected void logRequest() {
        if (!LOGGER.isLoggable(Level.INFO)) {
            return;
        }

        StringBuilder builder = new StringBuilder(super.toString());
        builder.append(System.lineSeparator());
        builder.append(this.loggedRequest.toString());
        builder.append(System.lineSeparator());

        LOGGER.log(Level.INFO, builder.toString());
    }

    private void writeBoundary() throws IOException {
        this.writeOutput("\r\n--");
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
        if (LOGGER.isLoggable(Level.INFO)) {
            this.loggedRequest.append(s);
        }
    }

    private void writeOutput(int b) throws IOException {
        this.outputStream.write(b);
    }
}
