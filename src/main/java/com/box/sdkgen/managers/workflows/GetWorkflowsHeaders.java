package com.box.sdkgen.managers.workflows;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetWorkflowsHeaders {

  public Map<String, String> extraHeaders;

  public GetWorkflowsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetWorkflowsHeaders(Builder builder) {
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

    public GetWorkflowsHeaders build() {
      return new GetWorkflowsHeaders(this);
    }
  }
}
