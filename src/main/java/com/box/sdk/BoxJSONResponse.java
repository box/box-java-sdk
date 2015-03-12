package com.box.sdk;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Used to read HTTP responses containing JSON from the Box API.
 *
 * <p>This request type extends BoxAPIResponse to provide additional functionality for handling JSON strings. It reads
 * the response body into a string and allows the JSON in the response to be logged.</p>
 */
public class BoxJSONResponse extends BoxAPIResponse {
    private static final int BUFFER_SIZE = 8192;

    private String json;

    /**
     * Constructs a BoxJSONResponse without an associated HttpURLConnection.
     */
    public BoxJSONResponse() {
        super();
    }

    /**
     * Constructs a BoxJSONResponse using an HttpURLConnection.
     * @param  connection a connection that has already sent a request to the API.
     */
    public BoxJSONResponse(HttpURLConnection connection) {
        super(connection);
    }

    /**
     * Gets the body of the response as a JSON string. When this method is called, the response's body will be read and
     * the response will be disconnected, meaning that the stream returned by {@link #getBody} can no longer be used.
     * @return the body of the response as a JSON string.
     */
    public String getJSON() {
        if (this.json != null) {
            return this.json;
        }

        InputStreamReader reader = new InputStreamReader(this.getBody(), StandardCharsets.UTF_8);
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[BUFFER_SIZE];

        try {
            int read = reader.read(buffer, 0, BUFFER_SIZE);
            while (read != -1) {
                builder.append(buffer, 0, read);
                read = reader.read(buffer, 0, BUFFER_SIZE);
            }

            this.disconnect();
            reader.close();
        } catch (IOException e) {
            throw new BoxAPIException("Couldn't connect to the Box API due to a network error.", e);
        }
        this.json = builder.toString();
        return this.json;
    }

    @Override
    protected String bodyToString() {
        String bodyString = super.bodyToString();
        if (bodyString == null) {
            return this.getJSON();
        } else {
            return bodyString;
        }
    }
}
