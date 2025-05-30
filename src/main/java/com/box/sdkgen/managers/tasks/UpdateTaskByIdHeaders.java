package com.box.sdkgen.managers.tasks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateTaskByIdHeaders {

  public Map<String, String> extraHeaders;

  public UpdateTaskByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateTaskByIdHeaders(UpdateTaskByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UpdateTaskByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public UpdateTaskByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UpdateTaskByIdHeaders build() {
      return new UpdateTaskByIdHeaders(this);
    }
  }
}
