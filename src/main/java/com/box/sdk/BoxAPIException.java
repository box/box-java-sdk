package com.box.sdk;

public class BoxAPIException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BoxAPIException(String message) {
        super(message);
    }

    public BoxAPIException(String message, Throwable cause) {
        super(message, cause);
    }
}
