package com.box.sdkgen.managers.chunkeduploads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UploadFilePartHeaders {

  public final String digest;

  public final String contentRange;

  public Map<String, String> extraHeaders;

  public UploadFilePartHeaders(String digest, String contentRange) {
    this.digest = digest;
    this.contentRange = contentRange;
    this.extraHeaders = mapOf();
  }

  protected UploadFilePartHeaders(Builder builder) {
    this.digest = builder.digest;
    this.contentRange = builder.contentRange;
    this.extraHeaders = builder.extraHeaders;
  }

  public String getDigest() {
    return digest;
  }

  public String getContentRange() {
    return contentRange;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected final String digest;

    protected final String contentRange;

    protected Map<String, String> extraHeaders;

    public Builder(String digest, String contentRange) {
      this.digest = digest;
      this.contentRange = contentRange;
      this.extraHeaders = mapOf();
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UploadFilePartHeaders build() {
      return new UploadFilePartHeaders(this);
    }
  }
}
