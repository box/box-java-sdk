package com.box.sdkgen.managers.retentionpolicyassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateRetentionPolicyAssignmentHeaders {

  public Map<String, String> extraHeaders;

  public CreateRetentionPolicyAssignmentHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateRetentionPolicyAssignmentHeaders(
      CreateRetentionPolicyAssignmentHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateRetentionPolicyAssignmentHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateRetentionPolicyAssignmentHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateRetentionPolicyAssignmentHeaders build() {
      return new CreateRetentionPolicyAssignmentHeaders(this);
    }
  }
}
