package com.box.sdkgen.managers.taskassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import java.util.Map;

public class GetTaskAssignmentByIdHeaders {

  public Map<String, String> extraHeaders;

  public GetTaskAssignmentByIdHeaders() {
    this.extraHeaders = mapOf();
  }

  protected GetTaskAssignmentByIdHeaders(Builder builder) {
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

    public GetTaskAssignmentByIdHeaders build() {
      return new GetTaskAssignmentByIdHeaders(this);
    }
  }
}
