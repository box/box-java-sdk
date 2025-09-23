package com.box.sdkgen.managers.users;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetUserMeHeaders {

  public Map<String, String> extraHeaders;

  public GetUserMeHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetUserMeHeaders(Builder builder) {
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

    public GetUserMeHeaders build() {
      return new GetUserMeHeaders(this);
    }
  }
}
