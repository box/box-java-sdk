package com.box.sdk;

public class BoxAPIException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String response;

    public BoxAPIException(String message) {
        super(message);

        this.response = null;
    }

    public BoxAPIException(String message, String response) {
        super(message);

        this.response = response;
    }

    public BoxAPIException(String message, Throwable cause) {
        super(message, cause);

        this.response = null;
    }

    public BoxAPIException(String message, String response, Throwable cause) {
        super(message, cause);

        this.response = response;
    }

    public String getResponse() {
        return this.response;
    }
}
