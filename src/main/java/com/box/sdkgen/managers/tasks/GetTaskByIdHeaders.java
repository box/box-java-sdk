package com.box.sdkgen.managers.tasks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetTaskByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetTaskByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetTaskByIdHeaders(Builder builder) {
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

    public GetTaskByIdHeaders build() {
      return new GetTaskByIdHeaders(this);
    }
  }
}
