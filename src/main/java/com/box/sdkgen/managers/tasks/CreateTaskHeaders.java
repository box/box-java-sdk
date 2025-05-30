package com.box.sdkgen.managers.tasks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateTaskHeaders {

  public Map<String, String> extraHeaders;

  public CreateTaskHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateTaskHeaders(CreateTaskHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateTaskHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateTaskHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateTaskHeaders build() {
      return new CreateTaskHeaders(this);
    }
  }
}
