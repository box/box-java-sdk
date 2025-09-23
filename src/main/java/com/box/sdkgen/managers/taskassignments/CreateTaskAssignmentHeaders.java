package com.box.sdkgen.managers.taskassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class CreateTaskAssignmentHeaders {

  public Map<String, String> extraHeaders;

  public CreateTaskAssignmentHeaders() {
    this.extraHeaders = mapOf();
  }

  protected CreateTaskAssignmentHeaders(Builder builder) {
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

    public CreateTaskAssignmentHeaders build() {
      return new CreateTaskAssignmentHeaders(this);
    }
  }
}
