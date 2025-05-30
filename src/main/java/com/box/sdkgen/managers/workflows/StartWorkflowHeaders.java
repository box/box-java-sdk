package com.box.sdkgen.managers.workflows;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class StartWorkflowHeaders {

  public Map<String, String> extraHeaders;

  public StartWorkflowHeaders() {
    this.extraHeaders = mapOf();
  }

  protected StartWorkflowHeaders(StartWorkflowHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class StartWorkflowHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public StartWorkflowHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public StartWorkflowHeaders build() {
      return new StartWorkflowHeaders(this);
    }
  }
}
