package com.box.sdkgen.managers.legalholdpolicyassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteLegalHoldPolicyAssignmentByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteLegalHoldPolicyAssignmentByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteLegalHoldPolicyAssignmentByIdHeaders(
      DeleteLegalHoldPolicyAssignmentByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteLegalHoldPolicyAssignmentByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteLegalHoldPolicyAssignmentByIdHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteLegalHoldPolicyAssignmentByIdHeaders build() {
      return new DeleteLegalHoldPolicyAssignmentByIdHeaders(this);
    }
  }
}
