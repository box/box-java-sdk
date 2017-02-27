package com.box.sdk;

import java.net.URL;

import com.box.sdk.http.HttpMethod;

/**
 * Used to make HTTP requests containing JSON to the Box API.
 *
 * <p>This request type extends BoxAPIRequest to provide additional functionality for handling JSON strings. It
 * automatically sets the appropriate "Content-Type" HTTP headers and allows the JSON in the request to be logged.</p>
 */
public class BoxJSONRequest extends BoxAPIRequest {
    private String json;

    /**
     * Constructs an authenticated BoxJSONRequest using a provided BoxAPIConnection.
     * @param  api    an API connection for authenticating the request.
     * @param  url    the URL of the request.
     * @param  method the HTTP method of the request.
     */
    public BoxJSONRequest(BoxAPIConnection api, URL url, String method) {
        super(api, url, method);

        this.addHeader("Content-Type", "application/json");
    }

    /**
     * Constructs an authenticated BoxJSONRequest using a provided BoxAPIConnection.
     * @param  api    an API connection for authenticating the request.
     * @param  url    the URL of the request.
     * @param  method the HTTP method of the request.
     */
    public BoxJSONRequest(BoxAPIConnection api, URL url, HttpMethod method) {
        super(api, url, method);

        this.addHeader("Content-Type", "application/json");
    }

    /**
     * Sets the body of this request to a given JSON string.
     * @param body the JSON string to use as the body.
     */
    @Override
    public void setBody(String body) {
        super.setBody(body);
        this.json = body;
    }

    @Override
    protected String bodyToString() {
        return this.json;
    }
}
