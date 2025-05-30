package com.box.sdkgen.managers.legalholdpolicyassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetLegalHoldPolicyAssignmentByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetLegalHoldPolicyAssignmentByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetLegalHoldPolicyAssignmentByIdHeaders(
      GetLegalHoldPolicyAssignmentByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetLegalHoldPolicyAssignmentByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetLegalHoldPolicyAssignmentByIdHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetLegalHoldPolicyAssignmentByIdHeaders build() {
      return new GetLegalHoldPolicyAssignmentByIdHeaders(this);
    }
  }
}
