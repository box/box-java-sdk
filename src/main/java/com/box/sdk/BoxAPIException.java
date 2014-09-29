package com.box.sdk;

public class BoxAPIException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final int responseCode;
    private final String response;

    public BoxAPIException(String message) {
        super(message);

        this.responseCode = 0;
        this.response = null;
    }

    public BoxAPIException(String message, int responseCode, String response) {
        super(message);

        this.responseCode = responseCode;
        this.response = response;
    }

    public BoxAPIException(String message, Throwable cause) {
        super(message, cause);

        this.responseCode = 0;
        this.response = null;
    }

    public BoxAPIException(String message, int responseCode, String response, Throwable cause) {
        super(message, cause);

        this.responseCode = responseCode;
        this.response = response;
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    public String getResponse() {
        return this.response;
    }
}
