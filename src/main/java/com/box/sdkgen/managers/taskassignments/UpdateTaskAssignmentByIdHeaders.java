package com.box.sdkgen.managers.taskassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class UpdateTaskAssignmentByIdHeaders {

  public Map<String, String> extraHeaders;

  public UpdateTaskAssignmentByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected UpdateTaskAssignmentByIdHeaders(Builder builder) {
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

    public UpdateTaskAssignmentByIdHeaders build() {
      return new UpdateTaskAssignmentByIdHeaders(this);
    }
  }
}
