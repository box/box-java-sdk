package com.box.sdkgen.managers.taskassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class DeleteTaskAssignmentByIdHeaders {

  public Map<String, String> extraHeaders;

  public DeleteTaskAssignmentByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected DeleteTaskAssignmentByIdHeaders(Builder builder) {
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

    public DeleteTaskAssignmentByIdHeaders build() {
      return new DeleteTaskAssignmentByIdHeaders(this);
    }
  }
}
