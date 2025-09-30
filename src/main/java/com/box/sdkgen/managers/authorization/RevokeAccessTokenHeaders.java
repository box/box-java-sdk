package com.box.sdkgen.managers.authorization;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class RevokeAccessTokenHeaders {

  public Map<String, String> extraHeaders;

  public RevokeAccessTokenHeaders() {
    this.extraHeaders = mapOf();
  }

  protected RevokeAccessTokenHeaders(Builder builder) {
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

    public RevokeAccessTokenHeaders build() {
      return new RevokeAccessTokenHeaders(this);
    }
  }
}
