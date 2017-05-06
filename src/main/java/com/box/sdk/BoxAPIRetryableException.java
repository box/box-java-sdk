package com.box.sdk;

/**
 * Thrown for rate limit responses: HTTP/1.1 429 Too Many Requests
 *
 * In such a case you will get a special header:
 *
 * Retry-After: {retry time in seconds}
 *
 * This class extends the BoxAPIException
 */
public class BoxAPIRetryableException extends BoxAPIException {

  int retryAfter;

  public BoxAPIRetryableException(String message, int retryAfter) {
    super(message);
  }

  public BoxAPIRetryableException(String message, int responseCode, String response, int retryAfter) {
    super(message, responseCode, response);
  }

  public BoxAPIRetryableException(String message, Throwable cause, int retryAfter) {
    super(message, cause);
  }

  public BoxAPIRetryableException(String message, int responseCode, String response, Throwable cause, int retryAfter) {
    super(message, responseCode, response, cause);
  }

  /**
   * Returns the number of seconds you have to wait before trying will be successful.
   */
  public int getRetryAfter() {
    return retryAfter;
  }

  public void setRetryAfter(int retryAfter) {
    this.retryAfter = retryAfter;
  }
}
