package com.box.sdk.http;

/**
 *
 */
public final class HttpHeaders {

    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_RANGE = "Content-Range";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String DIGEST = "Digest";
    public static final String IF_MATCH = "If-Match";
    public static final String IF_NONE_MATCH = "If-None-Match";
    public static final String X_BOX_PART_ID = "X-Box-Part-Id";

    //Prevents instantiation
    private HttpHeaders() {
    }
}
