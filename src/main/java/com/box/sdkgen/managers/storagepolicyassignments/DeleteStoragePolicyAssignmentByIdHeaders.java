package com.box.sdkgen.managers.storagepolicyassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteStoragePolicyAssignmentByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteStoragePolicyAssignmentByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteStoragePolicyAssignmentByIdHeaders(Builder builder) {
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

    public DeleteStoragePolicyAssignmentByIdHeaders build() {
      return new DeleteStoragePolicyAssignmentByIdHeaders(this);
    }
  }
}
