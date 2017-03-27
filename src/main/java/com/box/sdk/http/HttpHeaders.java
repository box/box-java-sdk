package com.box.sdk.http;

/**
 * HTTP header Key constants.
 */
public final class HttpHeaders {

    /**
     * HTTP header key Content-Length.
     */
    public static final String CONTENT_LENGTH = "Content-Length";

    /**
     * HTTP header key Content-Type.
     */
    public static final String CONTENT_TYPE = "Content-Type";

    /**
     * HTTP header key Content-Range.
     */
    public static final String CONTENT_RANGE = "Content-Range";

    /**
     * HTTP header key DIgest.
     */
    public static final String DIGEST = "Digest";

    /**
     * HTTP header key If-Match.
     */
    public static final String IF_MATCH = "If-Match";

    /**
     * HTTP header key If-None-Match.
     */
    public static final String IF_NONE_MATCH = "If-None-Match";

    /**
     * HTTP header key X-Box-Part-Id.
     */
    public static final String X_BOX_PART_ID = "X-Box-Part-Id";

    //Prevents instantiation
    private HttpHeaders() {
    }
}
