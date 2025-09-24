package com.box.sdkgen.managers.uploads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UploadWithPreflightCheckHeaders {

  public String contentMd5;

  public Map<String, String> extraHeaders;

  public UploadWithPreflightCheckHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UploadWithPreflightCheckHeaders(Builder builder) {
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

    public UploadWithPreflightCheckHeaders build() {
      return new UploadWithPreflightCheckHeaders(this);
    }
  }
}
