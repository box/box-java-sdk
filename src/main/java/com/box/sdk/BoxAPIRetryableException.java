package com.box.sdk;

/**
 * Thrown for rate limit responses: HTTP/1.1 429 Too Many Requests.
 * <p>
 * In such a case you will get a special header:
 * <p>
 * Retry-After: {retry time in seconds}
 * <p>
 * This class extends the BoxAPIException
 */
public class BoxAPIRetryableException extends BoxAPIException {
    private static final long serialVersionUID = 1L;

    private int retryAfter;

    /**
     * Constructs a BoxAPIRetryableException - these exceptions have a retry after header that they
     * should wait before being retried.
     * @param message The error message.
     * @param retryAfter The number of seconds to retry.
     * @param responseCode The https status code of the response
     * @param response String containing the json response of the error.
     */
    public BoxAPIRetryableException(String message, int responseCode, String response, int retryAfter) {
        super(message, responseCode, response);
        this.retryAfter = retryAfter;
    }

    /**
     * Returns the number of seconds you have to wait before trying will be successful.
     * @return The number of seconds to retry in seconds.
     */
    public int getRetryAfter() {
        return this.retryAfter;
    }

    /**
     * Set the retry after in seconds.
     * @param retryAfter How long to wait in seconds.
     */
    public void setRetryAfter(int retryAfter) {
        this.retryAfter = retryAfter;
    }
}
