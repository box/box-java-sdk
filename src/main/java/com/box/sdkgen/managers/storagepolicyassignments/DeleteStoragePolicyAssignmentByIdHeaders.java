package com.box.sdkgen.managers.storagepolicyassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteStoragePolicyAssignmentByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteStoragePolicyAssignmentByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteStoragePolicyAssignmentByIdHeaders(
      DeleteStoragePolicyAssignmentByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class DeleteStoragePolicyAssignmentByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public DeleteStoragePolicyAssignmentByIdHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public DeleteStoragePolicyAssignmentByIdHeaders build() {
      return new DeleteStoragePolicyAssignmentByIdHeaders(this);
    }
  }
}
