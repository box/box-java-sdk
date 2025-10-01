package com.box.sdkgen.managers.uploads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UploadFileHeaders {

  /**
   * An optional header containing the SHA1 hash of the file to ensure that the file was not
   * corrupted in transit.
   */
  public String contentMd5;

  /** Extra headers that will be included in the HTTP request. */
  public Map<String, String> extraHeaders;

  public UploadFileHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UploadFileHeaders(Builder builder) {
    this.contentMd5 = builder.contentMd5;
    this.extraHeaders = builder.extraHeaders;
  }

  public String getContentMd5() {
    return contentMd5;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected String contentMd5;

    protected Map<String, String> extraHeaders;

    public Builder() {
      this.extraHeaders = mapOf();
    }

    public Builder contentMd5(String contentMd5) {
      this.contentMd5 = contentMd5;
      return this;
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UploadFileHeaders build() {
      return new UploadFileHeaders(this);
    }
  }
}
