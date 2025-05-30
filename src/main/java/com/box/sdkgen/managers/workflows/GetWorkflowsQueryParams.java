package com.box.sdkgen.managers.workflows;

public class GetWorkflowsQueryParams {

  public final String folderId;

  public String triggerType;

  public Long limit;

  public String marker;

  public GetWorkflowsQueryParams(String folderId) {
    this.folderId = folderId;
  }

  protected GetWorkflowsQueryParams(GetWorkflowsQueryParamsBuilder builder) {
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

  public static class GetWorkflowsQueryParamsBuilder {

    protected final String folderId;

    protected String triggerType;

    protected Long limit;

    protected String marker;

    public GetWorkflowsQueryParamsBuilder(String folderId) {
      this.folderId = folderId;
    }

    public GetWorkflowsQueryParamsBuilder triggerType(String triggerType) {
      this.triggerType = triggerType;
      return this;
    }

    public GetWorkflowsQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetWorkflowsQueryParamsBuilder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetWorkflowsQueryParams build() {
      return new GetWorkflowsQueryParams(this);
    }
  }
}
