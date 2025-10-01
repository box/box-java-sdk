package com.box.sdkgen.managers.docgen;

public class GetDocgenJobsV2025R0QueryParams {

  /**
   * Defines the position marker at which to begin returning results. This is used when paginating
   * using marker-based pagination.
   *
   * <p>This requires `usemarker` to be set to `true`.
   */
  public String marker;

  /** The maximum number of items to return per page. */
  public Long limit;

  public GetDocgenJobsV2025R0QueryParams() {}

  protected GetDocgenJobsV2025R0QueryParams(Builder builder) {
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class Builder {

    protected String marker;

    protected Long limit;

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetDocgenJobsV2025R0QueryParams build() {
      return new GetDocgenJobsV2025R0QueryParams(this);
    }
  }
}
