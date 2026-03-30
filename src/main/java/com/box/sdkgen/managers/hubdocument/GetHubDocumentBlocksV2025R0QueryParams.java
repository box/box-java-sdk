package com.box.sdkgen.managers.hubdocument;

public class GetHubDocumentBlocksV2025R0QueryParams {

  /**
   * The unique identifier that represent a hub.
   *
   * <p>The ID for any hub can be determined by visiting this hub in the web application and copying
   * the ID from the URL. For example, for the URL `https://*.app.box.com/hubs/123` the `hub_id` is
   * `123`.
   */
  public final String hubId;

  /** The unique identifier of a page within the Box Hub. */
  public final String pageId;

  /**
   * Defines the position marker at which to begin returning results. This is used when paginating
   * using marker-based pagination.
   */
  public String marker;

  /** The maximum number of items to return per page. */
  public Long limit;

  public GetHubDocumentBlocksV2025R0QueryParams(String hubId, String pageId) {
    this.hubId = hubId;
    this.pageId = pageId;
  }

  protected GetHubDocumentBlocksV2025R0QueryParams(Builder builder) {
    this.hubId = builder.hubId;
    this.pageId = builder.pageId;
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public String getHubId() {
    return hubId;
  }

  public String getPageId() {
    return pageId;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class Builder {

    protected final String hubId;

    protected final String pageId;

    protected String marker;

    protected Long limit;

    public Builder(String hubId, String pageId) {
      this.hubId = hubId;
      this.pageId = pageId;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetHubDocumentBlocksV2025R0QueryParams build() {
      return new GetHubDocumentBlocksV2025R0QueryParams(this);
    }
  }
}
