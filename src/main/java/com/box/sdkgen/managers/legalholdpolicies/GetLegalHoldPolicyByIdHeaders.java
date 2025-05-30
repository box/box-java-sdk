package com.box.sdkgen.managers.legalholdpolicies;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetLegalHoldPolicyByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetLegalHoldPolicyByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetLegalHoldPolicyByIdHeaders(GetLegalHoldPolicyByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetLegalHoldPolicyByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetLegalHoldPolicyByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetLegalHoldPolicyByIdHeaders build() {
      return new GetLegalHoldPolicyByIdHeaders(this);
    }
  }
}
