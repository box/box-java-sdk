package com.box.sdkgen.managers.hubitems;

public class GetHubItemsV2025R0QueryParams {

  /**
   * The unique identifier that represent a hub.
   *
   * <p>The ID for any hub can be determined by visiting this hub in the web application and copying
   * the ID from the URL. For example, for the URL `https://*.app.box.com/hubs/123` the `hub_id` is
   * `123`.
   */
  public final String hubId;

  /**
   * The unique identifier of an item list block within the Box Hub.
   *
   * <p>When provided, the response will only include items that belong to the specified item list,
   * allowing you to filter results to items on a specific page or section.
   */
  public String parentId;

  /**
   * Defines the position marker at which to begin returning results. This is used when paginating
   * using marker-based pagination.
   *
   * <p>This requires `usemarker` to be set to `true`.
   */
  public String marker;

  /** The maximum number of items to return per page. */
  public Long limit;

  public GetHubItemsV2025R0QueryParams(String hubId) {
    this.hubId = hubId;
  }

  protected GetHubItemsV2025R0QueryParams(Builder builder) {
    this.hubId = builder.hubId;
    this.parentId = builder.parentId;
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public String getHubId() {
    return hubId;
  }

  public String getParentId() {
    return parentId;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class Builder {

    protected final String hubId;

    protected String parentId;

    protected String marker;

    protected Long limit;

    public Builder(String hubId) {
      this.hubId = hubId;
    }

    public Builder parentId(String parentId) {
      this.parentId = parentId;
      return this;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetHubItemsV2025R0QueryParams build() {
      return new GetHubItemsV2025R0QueryParams(this);
    }
  }
}
