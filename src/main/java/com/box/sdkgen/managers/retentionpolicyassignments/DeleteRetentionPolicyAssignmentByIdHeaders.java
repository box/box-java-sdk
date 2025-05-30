package com.box.sdkgen.managers.retentionpolicyassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteRetentionPolicyAssignmentByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteRetentionPolicyAssignmentByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteRetentionPolicyAssignmentByIdHeaders(
      DeleteRetentionPolicyAssignmentByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteRetentionPolicyAssignmentByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteRetentionPolicyAssignmentByIdHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteRetentionPolicyAssignmentByIdHeaders build() {
      return new DeleteRetentionPolicyAssignmentByIdHeaders(this);
    }
  }
}
