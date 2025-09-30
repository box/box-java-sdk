package com.box.sdkgen.managers.authorization;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class RefreshAccessTokenHeaders {

  public Map<String, String> extraHeaders;

  public RefreshAccessTokenHeaders() {
    this.extraHeaders = mapOf();
  }

  protected RefreshAccessTokenHeaders(Builder builder) {
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

    public RefreshAccessTokenHeaders build() {
      return new RefreshAccessTokenHeaders(this);
    }
  }
}
