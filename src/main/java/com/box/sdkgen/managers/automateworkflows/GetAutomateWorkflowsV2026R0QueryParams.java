package com.box.sdkgen.managers.automateworkflows;

public class GetAutomateWorkflowsV2026R0QueryParams {

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

  /** The maximum number of items to return per page. */
  public Long limit;

  /**
   * Defines the position marker at which to begin returning results. This is used when paginating
   * using marker-based pagination.
   */
  public String marker;

  public GetAutomateWorkflowsV2026R0QueryParams(String folderId) {
    this.folderId = folderId;
  }

  protected GetAutomateWorkflowsV2026R0QueryParams(Builder builder) {
    this.folderId = builder.folderId;
    this.limit = builder.limit;
    this.marker = builder.marker;
  }

  public String getFolderId() {
    return folderId;
  }

  public Long getLimit() {
    return limit;
  }

  public String getMarker() {
    return marker;
  }

  public static class Builder {

    protected final String folderId;

    protected Long limit;

    protected String marker;

    public Builder(String folderId) {
      this.folderId = folderId;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetAutomateWorkflowsV2026R0QueryParams build() {
      return new GetAutomateWorkflowsV2026R0QueryParams(this);
    }
  }
}
