package com.box.sdkgen.managers.legalholdpolicies;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteLegalHoldPolicyByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteLegalHoldPolicyByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteLegalHoldPolicyByIdHeaders(DeleteLegalHoldPolicyByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteLegalHoldPolicyByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteLegalHoldPolicyByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteLegalHoldPolicyByIdHeaders build() {
      return new DeleteLegalHoldPolicyByIdHeaders(this);
    }
  }
}
