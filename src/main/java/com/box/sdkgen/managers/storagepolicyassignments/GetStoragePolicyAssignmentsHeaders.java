package com.box.sdkgen.managers.storagepolicyassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetStoragePolicyAssignmentsHeaders {

  public Map<String, String> extraHeaders;

  public GetStoragePolicyAssignmentsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetStoragePolicyAssignmentsHeaders(GetStoragePolicyAssignmentsHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetStoragePolicyAssignmentsHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetStoragePolicyAssignmentsHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetStoragePolicyAssignmentsHeaders build() {
      return new GetStoragePolicyAssignmentsHeaders(this);
    }
  }
}
