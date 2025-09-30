package com.box.sdkgen.managers.groups;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteGroupByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteGroupByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteGroupByIdHeaders(Builder builder) {
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

    public DeleteGroupByIdHeaders build() {
      return new DeleteGroupByIdHeaders(this);
    }
  }
}
