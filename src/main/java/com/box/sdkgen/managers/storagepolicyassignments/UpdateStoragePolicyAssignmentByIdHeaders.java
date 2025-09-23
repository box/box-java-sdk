package com.box.sdkgen.managers.storagepolicyassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateStoragePolicyAssignmentByIdHeaders {

  public Map<String, String> extraHeaders;

  public UpdateStoragePolicyAssignmentByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateStoragePolicyAssignmentByIdHeaders(Builder builder) {
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

    public UpdateStoragePolicyAssignmentByIdHeaders build() {
      return new UpdateStoragePolicyAssignmentByIdHeaders(this);
    }
  }
}
