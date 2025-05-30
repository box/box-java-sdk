package com.box.sdkgen.managers.storagepolicyassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateStoragePolicyAssignmentByIdHeaders {

  public Map<String, String> extraHeaders;

  public UpdateStoragePolicyAssignmentByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateStoragePolicyAssignmentByIdHeaders(
      UpdateStoragePolicyAssignmentByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class UpdateStoragePolicyAssignmentByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public UpdateStoragePolicyAssignmentByIdHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public UpdateStoragePolicyAssignmentByIdHeaders build() {
      return new UpdateStoragePolicyAssignmentByIdHeaders(this);
    }
  }
}
