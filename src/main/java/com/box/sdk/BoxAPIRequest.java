package com.box.sdk;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BoxAPIRequest {
    private static final Logger LOGGER = Logger.getLogger(BoxFolder.class.getName());

    private final BoxAPIConnection api;
    private final URL url;
    private final List<RequestHeader> headers;
    private final String method;

    private int timeout;
    private InputStream body;
    private long bodyLength;
    private Map<String, List<String>> requestProperties;

    public BoxAPIRequest(URL url, String method) {
        this(null, url, method);
    }

    public BoxAPIRequest(BoxAPIConnection api, URL url, String method) {
        this.api = api;
        this.url = url;
        this.method = method;
        this.headers = new ArrayList<RequestHeader>();

        this.addHeader("Accept-Encoding", "gzip");
        this.addHeader("Accept-Charset", "utf-8");
    }

    public void addHeader(String key, String value) {
        this.headers.add(new RequestHeader(key, value));
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setContentLength(long length) {
        this.bodyLength = length;
    }

    public void setBody(InputStream stream) {
        this.body = stream;
    }

    public void setBody(String body) {
        this.bodyLength = body.length();
        this.body = new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));
    }

    public BoxAPIResponse send() {
        HttpURLConnection connection = this.createConnection();
        if (this.bodyLength > 0) {
            connection.setFixedLengthStreamingMode(this.bodyLength);
            connection.setDoOutput(true);
        }

        if (this.api != null) {
            connection.addRequestProperty("Authorization", "Bearer " + this.api.getAccessToken());
        }

        this.requestProperties = connection.getRequestProperties();
        this.writeBody(connection);

        // Ensure that we're connected in case writeBody() didn't write anything.
        try {
            connection.connect();
        } catch (IOException e) {
            throw new BoxAPIException("Couldn't connect to the Box API due to a network error.", e);
        }

        this.logRequest(connection);

        String contentType = connection.getContentType();
        BoxAPIResponse response;
        if (contentType == null) {
            response = new BoxAPIResponse(connection);
        } else if (contentType.contains("application/json")) {
            response = new BoxJSONResponse(connection);
        } else {
            response = new BoxAPIResponse(connection);
        }

        return response;
    }

    protected String bodyString() {
        return null;
    }

    protected void writeBody(HttpURLConnection connection) {
        if (this.body == null) {
            return;
        }

        connection.setDoOutput(true);
        try {
            OutputStream output = connection.getOutputStream();
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

    private void logRequest(HttpURLConnection connection) {
        if (!LOGGER.isLoggable(Level.INFO)) {
            return;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(this.method);
        builder.append(' ');
        builder.append(this.url.toString());
        builder.append(System.lineSeparator());

        for (Map.Entry<String, List<String>> entry : this.requestProperties.entrySet()) {
            builder.append(entry.getKey());
            builder.append(": ");
            for (String value : entry.getValue()) {
                builder.append(value);
                builder.append(", ");
            }

            builder.delete(builder.length() - 2, builder.length());
            builder.append(System.lineSeparator());
        }

        String bodyString = this.bodyString();
        if (bodyString != null) {
            builder.append(System.lineSeparator());
            builder.append(this.bodyString());
        }

        LOGGER.log(Level.INFO, builder.toString());
    }

    private HttpURLConnection createConnection() {
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) this.url.openConnection();
        } catch (IOException e) {
            throw new BoxAPIException("Couldn't connect to the Box API due to a network error.", e);
        }

        try {
            connection.setRequestMethod(this.method);
        } catch (ProtocolException e) {
            throw new BoxAPIException("Couldn't connect to the Box API because the request's method was invalid.", e);
        }

        connection.setConnectTimeout(this.timeout);
        connection.setReadTimeout(this.timeout);

        for (RequestHeader header : this.headers) {
            connection.addRequestProperty(header.getKey(), header.getValue());
        }

        return connection;
    }

    private final class RequestHeader {
        private final String key;
        private final String value;

        public RequestHeader(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return this.key;
        }

        public String getValue() {
            return this.value;
        }
    }
}
