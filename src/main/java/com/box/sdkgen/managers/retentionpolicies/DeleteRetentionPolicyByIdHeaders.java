package com.box.sdkgen.managers.retentionpolicies;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteRetentionPolicyByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteRetentionPolicyByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteRetentionPolicyByIdHeaders(DeleteRetentionPolicyByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteRetentionPolicyByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteRetentionPolicyByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteRetentionPolicyByIdHeaders build() {
      return new DeleteRetentionPolicyByIdHeaders(this);
    }
  }
}
