package com.box.sdkgen.managers.legalholdpolicyassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetLegalHoldPolicyAssignmentsHeaders {

  public Map<String, String> extraHeaders;

  public GetLegalHoldPolicyAssignmentsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetLegalHoldPolicyAssignmentsHeaders(
      GetLegalHoldPolicyAssignmentsHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetLegalHoldPolicyAssignmentsHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetLegalHoldPolicyAssignmentsHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetLegalHoldPolicyAssignmentsHeaders build() {
      return new GetLegalHoldPolicyAssignmentsHeaders(this);
    }
  }
}
