package com.box.sdkgen.managers.legalholdpolicyassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetLegalHoldPolicyAssignmentFileOnHoldHeaders {

  public Map<String, String> extraHeaders;

  public GetLegalHoldPolicyAssignmentFileOnHoldHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetLegalHoldPolicyAssignmentFileOnHoldHeaders(
      GetLegalHoldPolicyAssignmentFileOnHoldHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetLegalHoldPolicyAssignmentFileOnHoldHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetLegalHoldPolicyAssignmentFileOnHoldHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetLegalHoldPolicyAssignmentFileOnHoldHeaders build() {
      return new GetLegalHoldPolicyAssignmentFileOnHoldHeaders(this);
    }
  }
}
