package com.box.sdkgen.managers.emailaliases;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteUserEmailAliasByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteUserEmailAliasByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteUserEmailAliasByIdHeaders(Builder builder) {
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

    public DeleteUserEmailAliasByIdHeaders build() {
      return new DeleteUserEmailAliasByIdHeaders(this);
    }
  }
}
