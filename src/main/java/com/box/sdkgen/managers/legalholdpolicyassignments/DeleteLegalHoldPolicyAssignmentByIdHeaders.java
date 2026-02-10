package com.box.sdkgen.managers.legalholdpolicyassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteLegalHoldPolicyAssignmentByIdHeaders {

  /** Extra headers that will be included in the HTTP request. */
  public Map<String, String> extraHeaders;

  public DeleteLegalHoldPolicyAssignmentByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteLegalHoldPolicyAssignmentByIdHeaders(Builder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class Builder {

    protected Map<String, String> extraHeaders;

    public Builder() {}

    public Builder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteLegalHoldPolicyAssignmentByIdHeaders build() {
      if (this.extraHeaders == null) {
        this.extraHeaders = mapOf();
      }
      return new DeleteLegalHoldPolicyAssignmentByIdHeaders(this);
    }
  }
}
