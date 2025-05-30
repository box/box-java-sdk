package com.box.sdkgen.managers.taskassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetTaskAssignmentsHeaders {

  public Map<String, String> extraHeaders;

  public GetTaskAssignmentsHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetTaskAssignmentsHeaders(GetTaskAssignmentsHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetTaskAssignmentsHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetTaskAssignmentsHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetTaskAssignmentsHeaders build() {
      return new GetTaskAssignmentsHeaders(this);
    }
  }
}
