package com.box.sdkgen.managers.fileversions;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteFileVersionByIdHeaders {

  /**
   * Ensures this item hasn't recently changed before making changes.
   *
   * <p>Pass in the item's last observed `etag` value into this header and the endpoint will fail
   * with a `412 Precondition Failed` if it has changed since.
   */
  public String ifMatch;

  /** Extra headers that will be included in the HTTP request. */
  public Map<String, String> extraHeaders;

  public DeleteFileVersionByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteFileVersionByIdHeaders(Builder builder) {
    this.ifMatch = builder.ifMatch;
    this.extraHeaders = builder.extraHeaders;
  }

  public String getIfMatch() {
    return ifMatch;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected String ifMatch;

    protected Map<String, String> extraHeaders;

    public Builder() {}

    public Builder ifMatch(String ifMatch) {
      this.ifMatch = ifMatch;
      return this;
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteFileVersionByIdHeaders build() {
      if (this.extraHeaders == null) {
        this.extraHeaders = mapOf();
      }
      return new DeleteFileVersionByIdHeaders(this);
    }
  }
}
