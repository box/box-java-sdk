package com.box.sdk.http;

/**
 * HTTP Content-Type constants.
 */
public final class ContentType {

    /**
     * It is used when the HTTP request content type is application/json.
     */
    public static final String APPLICATION_JSON = "application/json";

    /**
     * It is used when the HTTP request content type is application/octet-stream.
     */
    public static final String APPLICATION_OCTET_STREAM = "application/octet-stream";

    //Prevents instantiation
    private ContentType() {
    }
}
