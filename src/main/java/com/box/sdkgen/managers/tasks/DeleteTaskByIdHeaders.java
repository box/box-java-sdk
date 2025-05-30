package com.box.sdkgen.managers.tasks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteTaskByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteTaskByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteTaskByIdHeaders(DeleteTaskByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteTaskByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteTaskByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteTaskByIdHeaders build() {
      return new DeleteTaskByIdHeaders(this);
    }
  }
}
