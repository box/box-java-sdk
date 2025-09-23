package com.box.sdkgen.managers.workflows;

public class GetWorkflowsQueryParams {

  public final String folderId;

  public String triggerType;

  public Long limit;

  public String marker;

  public GetWorkflowsQueryParams(String folderId) {
    this.folderId = folderId;
  }

  protected GetWorkflowsQueryParams(Builder builder) {
    this.folderId = builder.folderId;
    this.triggerType = builder.triggerType;
    this.limit = builder.limit;
    this.marker = builder.marker;
  }

  public String getFolderId() {
    return folderId;
  }

  public String getTriggerType() {
    return triggerType;
  }

  public Long getLimit() {
    return limit;
  }

  public String getMarker() {
    return marker;
  }

  public static class Builder {

    protected final String folderId;

    protected String triggerType;

    protected Long limit;

    protected String marker;

    public Builder(String folderId) {
      this.folderId = folderId;
    }

    public Builder triggerType(String triggerType) {
      this.triggerType = triggerType;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetWorkflowsQueryParams build() {
      return new GetWorkflowsQueryParams(this);
    }
  }
}
