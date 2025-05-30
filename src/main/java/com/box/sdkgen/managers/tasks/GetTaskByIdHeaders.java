package com.box.sdkgen.managers.tasks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetTaskByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetTaskByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetTaskByIdHeaders(GetTaskByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetTaskByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetTaskByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetTaskByIdHeaders build() {
      return new GetTaskByIdHeaders(this);
    }
  }
}
