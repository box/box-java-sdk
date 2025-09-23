package com.box.sdkgen.managers.emailaliases;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetUserEmailAliasesHeaders {

  public Map<String, String> extraHeaders;

  public GetUserEmailAliasesHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetUserEmailAliasesHeaders(Builder builder) {
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

    public GetUserEmailAliasesHeaders build() {
      return new GetUserEmailAliasesHeaders(this);
    }
  }
}
