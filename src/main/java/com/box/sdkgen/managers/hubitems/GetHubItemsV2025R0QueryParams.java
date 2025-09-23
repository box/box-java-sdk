package com.box.sdkgen.managers.hubitems;

public class GetHubItemsV2025R0QueryParams {

  public final String hubId;

  public String marker;

  public Long limit;

  public GetHubItemsV2025R0QueryParams(String hubId) {
    this.hubId = hubId;
  }

  protected GetHubItemsV2025R0QueryParams(Builder builder) {
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

    public GetHubItemsV2025R0QueryParams build() {
      return new GetHubItemsV2025R0QueryParams(this);
    }
  }
}
