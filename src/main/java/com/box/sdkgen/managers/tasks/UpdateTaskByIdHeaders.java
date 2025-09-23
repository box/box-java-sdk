package com.box.sdkgen.managers.tasks;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateTaskByIdHeaders {

  public Map<String, String> extraHeaders;

  public UpdateTaskByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateTaskByIdHeaders(Builder builder) {
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

    public UpdateTaskByIdHeaders build() {
      return new UpdateTaskByIdHeaders(this);
    }
  }
}
