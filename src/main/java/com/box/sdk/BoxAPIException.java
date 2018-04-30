package com.box.sdk;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Thrown to indicate that an error occurred while communicating with the Box API.
 */
public class BoxAPIException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private int responseCode;
    private String response;
    private Map<String, List<String>> headers;

    /**
     * Constructs a BoxAPIException with a specified message.
     * @param  message a message explaining why the exception occurred.
     */
    public BoxAPIException(String message) {
        super(message);

        this.responseCode = 0;
        this.response = null;
        this.headers = null;
    }

    /**
     * Constructs a BoxAPIException with details about the server's response.
     * @param  message      a message explaining why the exception occurred.
     * @param  responseCode the response code returned by the Box server.
     * @param  response     the response body returned by the Box server.
     */
    public BoxAPIException(String message, int responseCode, String response) {
        //People are missing the getResponse method we have. So adding it to message
        super(message + "\n" + response);

        this.responseCode = responseCode;
        this.response = response;
        this.headers = null;
    }

    /**
     * Constructs a BoxAPIException with details about the server's response, including response headers.
     * @param  message         a message explaining why the exception occurred.
     * @param  responseCode    the response code returned by the Box server.
     * @param  responseBody    the response body returned by the Box server.
     * @param  responseHeaders the response headers returned by the Box server.
     */
    public BoxAPIException(String message, int responseCode, String responseBody,
                           Map<String, List<String>> responseHeaders) {
        //People are missing the getResponse method we have. So adding it to message
        super(message + "\n" + responseBody);

        this.responseCode = responseCode;
        this.response = responseBody;
        this.headers = responseHeaders;
    }

    /**
     * Constructs a BoxAPIException that wraps another underlying exception.
     * @param  message a message explaining why the exception occurred.
     * @param  cause   an underlying exception.
     */
    public BoxAPIException(String message, Throwable cause) {
        super(message, cause);

        this.responseCode = 0;
        this.response = null;
        this.headers = null;
    }

    /**
     * Constructs a BoxAPIException that wraps another underlying exception with details about the server's response.
     * @param  message      a message explaining why the exception occurred.
     * @param  responseCode the response code returned by the Box server.
     * @param  response     the response body returned by the Box server.
     * @param  cause        an underlying exception.
     */
    public BoxAPIException(String message, int responseCode, String response, Throwable cause) {
        super(message, cause);

        this.responseCode = responseCode;
        this.response = response;
        this.headers = null;
    }

    /**
     * Constructs a BoxAPIException that includes the response headers.
     * @param message         a message explaining why the exception occurred.
     * @param responseCode    the response code returned by the Box server.
     * @param responseBody    the response body returned by the Box server.
     * @param responseHeaders the response headers returned by the Box server.
     * @param cause           an underlying exception.
     */
    public BoxAPIException(String message, int responseCode, String responseBody,
                           Map<String, List<String>> responseHeaders, Throwable cause) {

        super(message, cause);

        this.responseCode = responseCode;
        this.response = responseBody;
        this.headers = responseHeaders;
    }

    /**
     * Gets the response code returned by the server when this exception was thrown.
     * @return the response code returned by the server.
     */
    public int getResponseCode() {
        return this.responseCode;
    }

    /**
     * Gets the body of the response returned by the server when this exception was thrown.
     * @return the body of the response returned by the server.
     */
    public String getResponse() {
        return this.response;
    }

    /**
     * Gets the response headers, if available.
     * @return the response headers, or empty map if not available.
     */
    public Map<String, List<String>> getHeaders() {
        if (this.headers != null) {
            return this.headers;
        } else {
            return Collections.emptyMap();
        }
    }

    /**
     * Sets the response code returned by the server.
     * @param responseCode the response code returned by the server.
     */
    protected void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * Sets the response returned by ther server.
     * @param response the response returned by the server.
     */
    protected void setResponse(String response) {
        this.response = response;
    }

    /**
     * Sets the response headers.
     * @param headers headers to set.
     */
    protected void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }
}
