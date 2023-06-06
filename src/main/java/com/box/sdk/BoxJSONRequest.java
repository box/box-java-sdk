package com.box.sdk;

import static com.box.sdk.http.ContentType.APPLICATION_JSON;

import com.box.sdk.http.HttpMethod;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.net.URL;

/**
 * Used to make HTTP requests containing JSON to the Box API.
 *
 * <p>This request type extends BoxAPIRequest to provide additional functionality for handling JSON strings. It
 * automatically sets the appropriate "Content-Type" HTTP headers and allows the JSON in the request to be logged.</p>
 */
public class BoxJSONRequest extends BoxAPIRequest {
    private JsonValue jsonValue;

    protected BoxJSONRequest(BoxAPIConnection api, URL url, String method, String mediaType) {
        super(api, url, method, mediaType);
    }

    /**
     * Constructs an authenticated BoxJSONRequest using a provided BoxAPIConnection.
     *
     * @param api    an API connection for authenticating the request.
     * @param url    the URL of the request.
     * @param method the HTTP method of the request.
     */
    public BoxJSONRequest(BoxAPIConnection api, URL url, String method) {
        this(api, url, method, APPLICATION_JSON);
    }

    /**
     * Constructs an authenticated BoxJSONRequest using a provided BoxAPIConnection.
     *
     * @param api    an API connection for authenticating the request.
     * @param url    the URL of the request.
     * @param method the HTTP method of the request.
     */
    public BoxJSONRequest(BoxAPIConnection api, URL url, HttpMethod method) {
        this(api, url, method.name());
    }

    /**
     * Constructs an authenticated BoxJSONRequest.
     *
     * @param url    the URL of the request.
     * @param method the HTTP method of the request.
     */
    public BoxJSONRequest(URL url, HttpMethod method) {
        this(null, url, method);
    }

    /**
     * Sets the body of this request to a given JSON string.
     *
     * @param body the JSON string to use as the body.
     */
    @Override
    public void setBody(String body) {
        super.setBody(body);
        this.jsonValue = Json.parse(body);
    }

    /**
     * Sets the body of this request to a given JsonObject.
     *
     * @param body the JsonObject to use as the body.
     */
    public void setBody(JsonObject body) {
        super.setBody(body.toString());
        this.jsonValue = body;
    }

    /**
     * Gets the body of this request as a JsonObject.
     *
     * @return body represented as JsonObject.
     */
    public JsonObject getBodyAsJsonObject() {
        if (this.jsonValue.isObject()) {
            return this.jsonValue.asObject();
        }

        return null;
    }

    /**
     * Gets the body of this request as a {@link JsonValue}.
     *
     * @return body represented as JsonValue
     */
    public JsonValue getBodyAsJsonValue() {
        return this.jsonValue;
    }

    @Override
    public BoxJSONResponse send() {
        return super.send().toBoxJSONResponse();
    }

    @Override
    public BoxJSONResponse send(ProgressListener listener) {
        return super.send(listener).toBoxJSONResponse();
    }

    @Override
    protected String bodyToString() {
        return this.jsonValue != null ? this.jsonValue.toString() : null;
    }
}
