package com.box.sdkgen.managers.archives;

public class GetArchivesV2025R0QueryParams {

  /** The maximum number of items to return per page. */
  public Long limit;

  /**
   * Defines the position marker at which to begin returning results. This is used when paginating
   * using marker-based pagination.
   */
  public String marker;

  public GetArchivesV2025R0QueryParams() {}

  protected GetArchivesV2025R0QueryParams(Builder builder) {
    this.limit = builder.limit;
    this.marker = builder.marker;
  }

  public Long getLimit() {
    return limit;
  }

  public String getMarker() {
    return marker;
  }

  public static class Builder {

    protected Long limit;

    protected String marker;

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetArchivesV2025R0QueryParams build() {
      return new GetArchivesV2025R0QueryParams(this);
    }
  }
}
