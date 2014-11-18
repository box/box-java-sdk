package com.box.sdk;

/**
 * Thrown to indicate that an error occurred while communicating with the Box API.
 */
public class BoxAPIException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final int responseCode;
    private final String response;

    /**
     * Constructs a BoxAPIException with a specified message.
     * @param  message a message explaining why the exception occurred.
     */
    public BoxAPIException(String message) {
        super(message);

        this.responseCode = 0;
        this.response = null;
    }

    /**
     * Constructs a BoxAPIException with details about the server's response.
     * @param  message      a message explaining why the exception occurred.
     * @param  responseCode the response code returned by the Box server.
     * @param  response     the response body returned by the Box server.
     */
    public BoxAPIException(String message, int responseCode, String response) {
        super(message);

        this.responseCode = responseCode;
        this.response = response;
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
}
