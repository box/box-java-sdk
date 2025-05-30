package com.box.sdkgen.managers.uploads;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class PreflightFileUploadCheckHeaders {

  public Map<String, String> extraHeaders;

  public PreflightFileUploadCheckHeaders() {
    this.extraHeaders = mapOf();
  }

  protected PreflightFileUploadCheckHeaders(PreflightFileUploadCheckHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class PreflightFileUploadCheckHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public PreflightFileUploadCheckHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public PreflightFileUploadCheckHeaders build() {
      return new PreflightFileUploadCheckHeaders(this);
    }
  }
}
