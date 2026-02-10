package com.box.sdkgen.managers.chunkeduploads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateFileUploadSessionCommitByUrlHeaders {

  /**
   * The [RFC3230][1] message digest of the whole file.
   *
   * <p>Only SHA1 is supported. The SHA1 digest must be Base64 encoded. The format of this header is
   * as `sha=BASE64_ENCODED_DIGEST`.
   *
   * <p>[1]: https://tools.ietf.org/html/rfc3230
   */
  public final String digest;

  /**
   * Ensures this item hasn't recently changed before making changes.
   *
   * <p>Pass in the item's last observed `etag` value into this header and the endpoint will fail
   * with a `412 Precondition Failed` if it has changed since.
   */
  public String ifMatch;

  /**
   * Ensures an item is only returned if it has changed.
   *
   * <p>Pass in the item's last observed `etag` value into this header and the endpoint will fail
   * with a `304 Not Modified` if the item has not changed since.
   */
  public String ifNoneMatch;

  /** Extra headers that will be included in the HTTP request. */
  public Map<String, String> extraHeaders;

  public CreateFileUploadSessionCommitByUrlHeaders(String digest) {
    this.digest = digest;
    this.extraHeaders = mapOf();
  }

  protected CreateFileUploadSessionCommitByUrlHeaders(Builder builder) {
    this.digest = builder.digest;
    this.ifMatch = builder.ifMatch;
    this.ifNoneMatch = builder.ifNoneMatch;
    this.extraHeaders = builder.extraHeaders;
  }

  public String getDigest() {
    return digest;
  }

  public String getIfMatch() {
    return ifMatch;
  }

  public String getIfNoneMatch() {
    return ifNoneMatch;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected final String digest;

    protected String ifMatch;

    protected String ifNoneMatch;

    protected Map<String, String> extraHeaders;

    public Builder(String digest) {
      this.digest = digest;
    }

    public Builder ifMatch(String ifMatch) {
      this.ifMatch = ifMatch;
      return this;
    }

    public Builder ifNoneMatch(String ifNoneMatch) {
      this.ifNoneMatch = ifNoneMatch;
      return this;
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateFileUploadSessionCommitByUrlHeaders build() {
      if (this.extraHeaders == null) {
        this.extraHeaders = mapOf();
      }
      return new CreateFileUploadSessionCommitByUrlHeaders(this);
    }
  }
}
