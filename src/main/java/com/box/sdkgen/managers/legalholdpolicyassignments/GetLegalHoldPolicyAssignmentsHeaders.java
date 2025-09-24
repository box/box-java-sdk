package com.box.sdkgen.managers.legalholdpolicyassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetLegalHoldPolicyAssignmentsHeaders {

  public Map<String, String> extraHeaders;

  public GetLegalHoldPolicyAssignmentsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetLegalHoldPolicyAssignmentsHeaders(Builder builder) {
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

    public GetLegalHoldPolicyAssignmentsHeaders build() {
      return new GetLegalHoldPolicyAssignmentsHeaders(this);
    }
  }
}
