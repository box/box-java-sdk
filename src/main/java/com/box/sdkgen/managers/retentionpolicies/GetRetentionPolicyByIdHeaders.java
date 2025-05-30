package com.box.sdkgen.managers.retentionpolicies;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetRetentionPolicyByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetRetentionPolicyByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetRetentionPolicyByIdHeaders(GetRetentionPolicyByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetRetentionPolicyByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetRetentionPolicyByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetRetentionPolicyByIdHeaders build() {
      return new GetRetentionPolicyByIdHeaders(this);
    }
  }
}
