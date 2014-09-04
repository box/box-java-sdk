package com.box.sdk;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BoxAPIRequest {
    private static final Logger LOGGER = Logger.getLogger(BoxFolder.class.getName());

    private final HttpURLConnection connection;
    private Map<String, List<String>> requestHeaders;
    private InputStream body;
    private long bodyLength;
    private boolean connected;
    private boolean sent;

    public BoxAPIRequest(URL url, String method) {
        this(null, url, method);
    }

    public BoxAPIRequest(BoxAPIConnection api, URL url, String method) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            assert false : "An IOException indicates an invalid URL, which is a bug in the SDK.";
        }
        this.connection = connection;

        this.connection.addRequestProperty("Accept-Encoding", "gzip");
        this.connection.addRequestProperty("Accept-Charset", "utf-8");

        if (api != null) {
            this.connection.addRequestProperty("Authorization", "Bearer " + api.getAccessToken());
        }

        try {
            this.connection.setRequestMethod(method);
        } catch (ProtocolException e) {
            assert false : "An invalid HTTP method indicates a bug in the SDK.";
        }
    }

    public void addHeader(String key, String value) {
        this.connection.addRequestProperty(key, value);
    }

    public void setTimeout(int timeout) {
        this.connection.setConnectTimeout(timeout);
        this.connection.setReadTimeout(timeout);
    }

    public void setBody(InputStream stream) {
        this.body = stream;
    }

    public void setBody(String body) {
        this.bodyLength = body.length();
        this.body = new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));
    }

    public BoxAPIResponse send() {
        if (this.sent) {
            throw new IllegalStateException("Cannot send the request because it has already been sent.");
        }
        this.sent = true;

        this.requestHeaders = this.connection.getRequestProperties();
        this.writeBody();
        try {
            this.connection.connect();
        } catch (IOException e) {
            throw new BoxAPIException("Couldn't connect to the Box API due to a network error.", e);
        }
        this.logRequest();

        String contentType = this.connection.getContentType();
        BoxAPIResponse response;
        if (contentType == null) {
            response = new BoxAPIResponse(this.connection);
        } else if (contentType.contains("application/json")) {
            response = new BoxJSONResponse(this.connection);
        } else {
            response = new BoxAPIResponse(this.connection);
        }

        return response;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.connection.getRequestMethod());
        builder.append(' ');
        builder.append(this.connection.getURL().toString());
        builder.append(System.lineSeparator());

        for (Map.Entry<String, List<String>> entry : this.requestHeaders.entrySet()) {
            builder.append(entry.getKey());
            builder.append(": ");
            for (String value : entry.getValue()) {
                builder.append(value);
                builder.append(", ");
            }

            builder.delete(builder.length() - 2, builder.length());
            builder.append(System.lineSeparator());
        }

        return builder.toString();
    }

    protected void logRequest() {
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.log(Level.INFO, this.toString());
        }
    }

    protected HttpURLConnection getConnection() {
        return this.connection;
    }

    protected void writeBody() {
        if (this.body == null) {
            return;
        }

        if (this.bodyLength > 0) {
            this.connection.setFixedLengthStreamingMode(this.bodyLength);
        }

        this.connection.setDoOutput(true);
        try {
            OutputStream output = this.connection.getOutputStream();
            int b = this.body.read();
            while (b != -1) {
                output.write(b);
                b = this.body.read();
            }
            output.close();
        } catch (IOException e) {
            throw new BoxAPIException("Couldn't connect to the Box API due to a network error.", e);
        }
    }
}
