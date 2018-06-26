package com.box.sdk;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Map;

import com.eclipsesource.json.JsonObject;

/**
 * Used to read HTTP responses containing JSON from the Box API.
 *
 * <p>This request type extends BoxAPIResponse to provide additional functionality for handling JSON strings. It reads
 * the response body into a string and allows the JSON in the response to be logged.</p>
 */
public class BoxJSONResponse extends BoxAPIResponse {
    private static final int BUFFER_SIZE = 8192;
    private JsonObject jsonObject;

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
     * Constructs a BoxAPIResponse with an http response code and response body.
     * @param responseCode http response code
     * @param httpHeaders map of http headers
     * @param body response body as Json Object
     */
    public BoxJSONResponse(int responseCode, Map<String, String> httpHeaders, JsonObject body) {
        super(responseCode, httpHeaders);
        this.jsonObject = body;
    }

    /**
     * Get response as Json Object.
     * @return response as JsonObject
     */
    public JsonObject getJsonObject() {
        if (this.jsonObject != null) {
            return this.jsonObject;
        } else {
            return JsonObject.readFrom(this.getJSON());
        }
    }

    /**
     * Gets the body of the response as a JSON string. When this method is called, the response's body will be read and
     * the response will be disconnected, meaning that the stream returned by {@link #getBody} can no longer be used.
     * @return the body of the response as a JSON string.
     */
    public String getJSON() {
        if (this.jsonObject != null) {
            return this.jsonObject.toString();
        } else {
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
            this.jsonObject = JsonObject.readFrom(builder.toString());
            return builder.toString();
        }
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
