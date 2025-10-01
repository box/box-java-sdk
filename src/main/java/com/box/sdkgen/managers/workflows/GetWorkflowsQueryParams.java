package com.box.sdkgen.managers.workflows;

public class GetWorkflowsQueryParams {

  /**
   * The unique identifier that represent a folder.
   *
   * <p>The ID for any folder can be determined by visiting this folder in the web application and
   * copying the ID from the URL. For example, for the URL `https://*.app.box.com/folder/123` the
   * `folder_id` is `123`.
   *
   * <p>The root folder of a Box account is always represented by the ID `0`.
   */
  public final String folderId;

  /** Type of trigger to search for. */
  public String triggerType;

  /** The maximum number of items to return per page. */
  public Long limit;

  /**
   * Defines the position marker at which to begin returning results. This is used when paginating
   * using marker-based pagination.
   *
   * <p>This requires `usemarker` to be set to `true`.
   */
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
