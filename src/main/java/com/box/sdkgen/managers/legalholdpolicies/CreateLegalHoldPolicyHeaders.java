package com.box.sdkgen.managers.legalholdpolicies;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateLegalHoldPolicyHeaders {

  public Map<String, String> extraHeaders;

  public CreateLegalHoldPolicyHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateLegalHoldPolicyHeaders(CreateLegalHoldPolicyHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateLegalHoldPolicyHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateLegalHoldPolicyHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateLegalHoldPolicyHeaders build() {
      return new CreateLegalHoldPolicyHeaders(this);
    }
  }
}
