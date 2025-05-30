package com.box.sdkgen.managers.storagepolicyassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateStoragePolicyAssignmentHeaders {

  public Map<String, String> extraHeaders;

  public CreateStoragePolicyAssignmentHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateStoragePolicyAssignmentHeaders(
      CreateStoragePolicyAssignmentHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateStoragePolicyAssignmentHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateStoragePolicyAssignmentHeadersBuilder extraHeaders(
        Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateStoragePolicyAssignmentHeaders build() {
      return new CreateStoragePolicyAssignmentHeaders(this);
    }
  }
}
