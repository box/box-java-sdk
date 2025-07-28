package com.box.sdkgen.managers.users;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateUserByIdHeaders {

  public Map<String, String> extraHeaders;

  public UpdateUserByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateUserByIdHeaders(Builder builder) {
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

    public UpdateUserByIdHeaders build() {
      return new UpdateUserByIdHeaders(this);
    }
  }
}
