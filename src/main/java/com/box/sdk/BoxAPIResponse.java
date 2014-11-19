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

/**
 * Used to read HTTP responses from the Box API.
 *
 * <p>All responses from the REST API are read using this class or one of its subclasses. This class wraps {@link
 * HttpURLConnection} in order to provide a simpler interface that can automatically handle various conditions specific
 * to Box's API. When a response is contructed, it will throw a {@link BoxAPIException} if the response from the API
 * was an error. Therefore every BoxAPIResponse instance is guaranteed to represent a successful response.</p>
 *
 * <p>This class usually isn't instantiated directly, but is instead returned after calling {@link BoxAPIRequest#send}.
 * </p>
 */
public class BoxAPIResponse {
    private static final Logger LOGGER = Logger.getLogger(BoxFolder.class.getName());
    private static final int BUFFER_SIZE = 8192;

    private final HttpURLConnection connection;
    private InputStream inputStream;
    private int responseCode;
    private String bodyString;

    /**
     * Constructs a BoxAPIResponse using an HttpURLConnection.
     * @param  connection a connection that has already sent a request to the API.
     */
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

    /**
     * Gets the response code returned by the API.
     * @return the response code returned by the API.
     */
    public int getResponseCode() {
        return this.responseCode;
    }

    /**
     * Gets the length of this response's body as indicated by the "Content-Length" header.
     * @return the length of the response's body.
     */
    public long getContentLength() {
        return this.connection.getContentLengthLong();
    }

    /**
     * Gets an InputStream for reading this response's body.
     * @return an InputStream for reading the response's body.
     */
    public InputStream getBody() {
        return this.getBody(null);
    }

    /**
     * Gets an InputStream for reading this response's body which will report its read progress to a ProgressListener.
     * @param  listener a listener for monitoring the read progress of the body.
     * @return an InputStream for reading the response's body.
     */
    public InputStream getBody(ProgressListener listener) {
        if (this.inputStream == null) {
            String contentEncoding = this.connection.getContentEncoding();
            try {
                if (listener == null) {
                    this.inputStream = this.connection.getInputStream();
                } else {
                    this.inputStream = new ProgressInputStream(this.connection.getInputStream(), listener,
                        this.getContentLength());
                }

                if (contentEncoding != null && contentEncoding.equalsIgnoreCase("gzip")) {
                    this.inputStream = new GZIPInputStream(this.inputStream);
                }
            } catch (IOException e) {
                throw new BoxAPIException("Couldn't connect to the Box API due to a network error.", e);
            }
        }

        return this.inputStream;
    }

    /**
     * Disconnects this response from the server and frees up any network resources. The body of this response can no
     * longer be read after it has been disconnected.
     */
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

    /**
     * Returns a string representation of this response's body. This method is used when logging this response's body.
     * By default, it returns an empty string (to avoid accidentally logging binary data) unless the response contained
     * an error message.
     * @return a string representation of this response's body.
     */
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
