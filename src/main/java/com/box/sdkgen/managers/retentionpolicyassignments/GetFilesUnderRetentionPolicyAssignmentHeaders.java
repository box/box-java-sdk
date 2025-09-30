package com.box.sdkgen.managers.retentionpolicyassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetFilesUnderRetentionPolicyAssignmentHeaders {

  public Map<String, String> extraHeaders;

  public GetFilesUnderRetentionPolicyAssignmentHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetFilesUnderRetentionPolicyAssignmentHeaders(Builder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected Map<String, String> extraHeaders;

    public Builder() {
      this.extraHeaders = mapOf();
    }

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetFilesUnderRetentionPolicyAssignmentHeaders build() {
      return new GetFilesUnderRetentionPolicyAssignmentHeaders(this);
    }
  }
}
