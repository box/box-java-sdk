package com.box.sdkgen.managers.taskassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetTaskAssignmentByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetTaskAssignmentByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetTaskAssignmentByIdHeaders(GetTaskAssignmentByIdHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class GetTaskAssignmentByIdHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public GetTaskAssignmentByIdHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public GetTaskAssignmentByIdHeaders build() {
      return new GetTaskAssignmentByIdHeaders(this);
    }
  }
}
