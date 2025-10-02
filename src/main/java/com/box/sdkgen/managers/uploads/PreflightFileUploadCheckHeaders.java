package com.box.sdkgen.managers.uploads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class PreflightFileUploadCheckHeaders {

  /** Extra headers that will be included in the HTTP request. */
  public Map<String, String> extraHeaders;

  public PreflightFileUploadCheckHeaders() {
    this.extraHeaders = mapOf();
  }

  protected PreflightFileUploadCheckHeaders(Builder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected Map<String, String> extraHeaders;

    public Builder() {
      this.extraHeaders = mapOf();
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public PreflightFileUploadCheckHeaders build() {
      return new PreflightFileUploadCheckHeaders(this);
    }
  }
}
