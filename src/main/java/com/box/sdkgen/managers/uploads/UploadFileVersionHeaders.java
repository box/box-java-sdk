package com.box.sdkgen.managers.uploads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UploadFileVersionHeaders {

  /**
   * Ensures this item hasn't recently changed before making changes.
   *
   * <p>Pass in the item's last observed `etag` value into this header and the endpoint will fail
   * with a `412 Precondition Failed` if it has changed since.
   */
  public String ifMatch;

  /**
   * An optional header containing the SHA1 hash of the file to ensure that the file was not
   * corrupted in transit.
   */
  public String contentMd5;

  /** Extra headers that will be included in the HTTP request. */
  public Map<String, String> extraHeaders;

  public UploadFileVersionHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UploadFileVersionHeaders(Builder builder) {
    this.ifMatch = builder.ifMatch;
    this.contentMd5 = builder.contentMd5;
    this.extraHeaders = builder.extraHeaders;
  }

  public String getIfMatch() {
    return ifMatch;
  }

  public String getContentMd5() {
    return contentMd5;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected String ifMatch;

    protected String contentMd5;

    protected Map<String, String> extraHeaders;

    public Builder() {}

    public Builder ifMatch(String ifMatch) {
      this.ifMatch = ifMatch;
      return this;
    }

    public Builder contentMd5(String contentMd5) {
      this.contentMd5 = contentMd5;
      return this;
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UploadFileVersionHeaders build() {
      if (this.extraHeaders == null) {
        this.extraHeaders = mapOf();
      }
      return new UploadFileVersionHeaders(this);
    }
  }
}
