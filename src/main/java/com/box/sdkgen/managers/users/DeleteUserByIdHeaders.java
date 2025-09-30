package com.box.sdkgen.managers.users;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteUserByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteUserByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteUserByIdHeaders(Builder builder) {
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

    public DeleteUserByIdHeaders build() {
      return new DeleteUserByIdHeaders(this);
    }
  }
}
