package com.box.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

public class BoxAPIResponse {
    private static final Logger LOGGER = Logger.getLogger(BoxFolder.class.getName());
    private static final int BUFFER_SIZE = 8192;

    private final HttpURLConnection connection;
    private InputStream inputStream;
    private int responseCode;
    private String bodyString;

    public BoxAPIResponse(HttpURLConnection connection) {
        this.connection = connection;
        this.inputStream = null;

        try {
            this.responseCode = this.connection.getResponseCode();
        } catch (IOException e) {
            throw new BoxAPIException("Couldn't connect to the Box API due to a network error.", e);
        }

        if (!isSuccess(this.responseCode)) {
            this.logResponse();
            throw new BoxAPIException("The API returned an error code: " + this.responseCode, this.responseCode,
                this.bodyToString());
        }

        this.logResponse();
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    public long getContentLength() {
        return this.connection.getContentLengthLong();
    }

    public InputStream getBody() {
        if (this.inputStream == null) {
            String contentEncoding = this.connection.getContentEncoding();
            try {
                if (contentEncoding != null && contentEncoding.equalsIgnoreCase("gzip")) {
                    this.inputStream = new GZIPInputStream(this.connection.getInputStream());
                } else {
                    this.inputStream = this.connection.getInputStream();
                }
            } catch (IOException e) {
                throw new BoxAPIException("Couldn't connect to the Box API due to a network error.", e);
            }
        }

        return this.inputStream;
    }

    public void disconnect() {
        if (this.inputStream != null) {
            try {
                this.inputStream.close();
            } catch (IOException e) {
                throw new BoxAPIException("Couldn't connect to the Box API due to a network error.", e);
            }
        }
    }

    @Override
    public String toString() {
        Map<String, List<String>> headers = this.connection.getHeaderFields();
        StringBuilder builder = new StringBuilder();
        builder.append("Response");
        builder.append(System.lineSeparator());
        builder.append(this.connection.getRequestMethod());
        builder.append(' ');
        builder.append(this.connection.getURL().toString());
        builder.append(System.lineSeparator());
        builder.append(headers.get(null).get(0));
        builder.append(System.lineSeparator());

        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            String key = entry.getKey();
            if (key == null) {
                continue;
            }

            List<String> nonEmptyValues = new ArrayList<String>();
            for (String value : entry.getValue()) {
                if (value != null && value.trim().length() != 0) {
                    nonEmptyValues.add(value);
                }
            }

            if (nonEmptyValues.size() == 0) {
                continue;
            }

            builder.append(key);
            builder.append(": ");
            for (String value : nonEmptyValues) {
                builder.append(value);
                builder.append(", ");
            }

            builder.delete(builder.length() - 2, builder.length());
            builder.append(System.lineSeparator());
        }

        String bodyString = this.bodyToString();
        if (bodyString != null && bodyString != "") {
            builder.append(System.lineSeparator());
            builder.append(bodyString);
        }

        return builder.toString().trim();
    }

    protected String bodyToString() {
        if (this.bodyString == null && !isSuccess(this.responseCode)) {
            this.bodyString = readErrorStream(this.connection.getErrorStream());
        }

        return this.bodyString;
    }

    private void logResponse() {
        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, this.toString());
        }
    }

    private static boolean isSuccess(int responseCode) {
        return responseCode >= 200 && responseCode < 300;
    }

    private static String readErrorStream(InputStream stream) {
        if (stream == null) {
            return null;
        }

        InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[BUFFER_SIZE];

        try {
            int read = reader.read(buffer, 0, BUFFER_SIZE);
            while (read != -1) {
                builder.append(buffer, 0, read);
                read = reader.read(buffer, 0, BUFFER_SIZE);
            }

            reader.close();
        } catch (IOException e) {
            return null;
        }

        return builder.toString();
    }
}
