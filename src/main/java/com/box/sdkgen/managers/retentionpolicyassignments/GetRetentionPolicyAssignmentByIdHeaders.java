package com.box.sdkgen.managers.retentionpolicyassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetRetentionPolicyAssignmentByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetRetentionPolicyAssignmentByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetRetentionPolicyAssignmentByIdHeaders(Builder builder) {
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

    public GetRetentionPolicyAssignmentByIdHeaders build() {
      return new GetRetentionPolicyAssignmentByIdHeaders(this);
    }
  }
}
