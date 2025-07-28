package com.box.sdkgen.managers.legalholdpolicies;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetLegalHoldPolicyByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetLegalHoldPolicyByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetLegalHoldPolicyByIdHeaders(Builder builder) {
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

    public GetLegalHoldPolicyByIdHeaders build() {
      return new GetLegalHoldPolicyByIdHeaders(this);
    }
  }
}
