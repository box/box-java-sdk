package com.box.sdkgen.managers.tasks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFileTasksHeaders {

  public Map<String, String> extraHeaders;

  public GetFileTasksHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFileTasksHeaders(GetFileTasksHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetFileTasksHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetFileTasksHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFileTasksHeaders build() {
      return new GetFileTasksHeaders(this);
    }
  }
}
