package com.box.sdkgen.managers.retentionpolicies;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateRetentionPolicyHeaders {

  public Map<String, String> extraHeaders;

  public CreateRetentionPolicyHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateRetentionPolicyHeaders(CreateRetentionPolicyHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateRetentionPolicyHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateRetentionPolicyHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateRetentionPolicyHeaders build() {
      return new CreateRetentionPolicyHeaders(this);
    }
  }
}
