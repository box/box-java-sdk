package com.box.sdkgen.managers.hubcollaborations;

public class GetHubCollaborationsV2025R0QueryParams {

  /**
   * The unique identifier that represent a hub.
   *
   * <p>The ID for any hub can be determined by visiting this hub in the web application and copying
   * the ID from the URL. For example, for the URL `https://*.app.box.com/hubs/123` the `hub_id` is
   * `123`.
   */
  public final String hubId;

  /**
   * Defines the position marker at which to begin returning results. This is used when paginating
   * using marker-based pagination.
   *
   * <p>This requires `usemarker` to be set to `true`.
   */
  public String marker;

  /** The maximum number of items to return per page. */
  public Long limit;

  public GetHubCollaborationsV2025R0QueryParams(String hubId) {
    this.hubId = hubId;
  }

  protected GetHubCollaborationsV2025R0QueryParams(Builder builder) {
    this.hubId = builder.hubId;
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public String getHubId() {
    return hubId;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class Builder {

    protected final String hubId;

    protected String marker;

    protected Long limit;

    public Builder(String hubId) {
      this.hubId = hubId;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetHubCollaborationsV2025R0QueryParams build() {
      return new GetHubCollaborationsV2025R0QueryParams(this);
    }
  }
}
