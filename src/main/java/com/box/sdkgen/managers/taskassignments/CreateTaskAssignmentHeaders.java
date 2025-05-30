package com.box.sdkgen.managers.taskassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateTaskAssignmentHeaders {

  public Map<String, String> extraHeaders;

  public CreateTaskAssignmentHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateTaskAssignmentHeaders(CreateTaskAssignmentHeadersBuilder builder) {
    this.extraHeaders = builder.extraHeaders;
  }

  public Map<String, String> getExtraHeaders() {
    return extraHeaders;
  }

  public static class CreateTaskAssignmentHeadersBuilder {

    protected Map<String, String> extraHeaders;

    public CreateTaskAssignmentHeadersBuilder extraHeaders(Map<String, String> extraHeaders) {
      this.extraHeaders = extraHeaders;
      return this;
    }

    public CreateTaskAssignmentHeaders build() {
      return new CreateTaskAssignmentHeaders(this);
    }
  }
}
