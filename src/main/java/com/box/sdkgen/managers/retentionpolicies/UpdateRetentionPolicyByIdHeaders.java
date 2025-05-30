package com.box.sdkgen.managers.retentionpolicies;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateRetentionPolicyByIdHeaders {

  public Map<String, String> extraHeaders;

  public UpdateRetentionPolicyByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateRetentionPolicyByIdHeaders(UpdateRetentionPolicyByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UpdateRetentionPolicyByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public UpdateRetentionPolicyByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UpdateRetentionPolicyByIdHeaders build() {
      return new UpdateRetentionPolicyByIdHeaders(this);
    }
  }
}
