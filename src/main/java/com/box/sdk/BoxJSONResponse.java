package com.box.sdk;

import com.box.sdk.http.ContentType;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.ParseException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

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

    BoxJSONResponse(BoxAPIResponse response) {
        this(
            response.getResponseCode(),
            response.getRequestMethod(),
            response.getRequestUrl(),
            response.getHeaders(),
            new JsonObject()
        );
    }

    /**
     * Constructs a BoxAPIResponse with an http response code and response body.
     *
     * @param responseCode http response code
     * @param headers  map of http headers
     * @param body         response body as Json Object
     */
    public BoxJSONResponse(int responseCode,
                           String requestMethod,
                           String requestUrl,
                           Map<String, List<String>> headers,
                           JsonObject body
    ) {
        super(responseCode, requestMethod, requestUrl, headers, body.toString(), ContentType.APPLICATION_JSON);
        this.jsonObject = body;
    }

    /**
     * Get response as Json Object.
     *
     * @return response as JsonObject
     */
    public JsonObject getJsonObject() {
        if (this.jsonObject != null) {
            return this.jsonObject;
        } else {
            return Json.parse(this.getJSON()).asObject();
        }
    }

    /**
     * Gets the body of the response as a JSON string. When this method is called, the response's body will be read and
     * the response will be disconnected, meaning that the stream returned by {@link #getBody} can no longer be used.
     *
     * @return the body of the response as a JSON string.
     */
    public String getJSON() {
        if (this.jsonObject != null) {
            return this.jsonObject.toString();
        } else if (this.getBody() == null) {
            return null;
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

                this.close();
                reader.close();
            } catch (IOException e) {
                throw new BoxAPIException("Couldn't connect to the Box API due to a network error.", e);
            }
            String jsonAsString = builder.toString();
            try {
                this.jsonObject = Json.parse(jsonAsString).asObject();
            } catch (ParseException e) {
                throw new RuntimeException("Error parsing JSON:\n" + jsonAsString, e);
            }
            return jsonAsString;
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
