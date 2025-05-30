package com.box.sdkgen.managers.legalholdpolicies;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateLegalHoldPolicyByIdHeaders {

  public Map<String, String> extraHeaders;

  public UpdateLegalHoldPolicyByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateLegalHoldPolicyByIdHeaders(UpdateLegalHoldPolicyByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UpdateLegalHoldPolicyByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public UpdateLegalHoldPolicyByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UpdateLegalHoldPolicyByIdHeaders build() {
      return new UpdateLegalHoldPolicyByIdHeaders(this);
    }
  }
}
