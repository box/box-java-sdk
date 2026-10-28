package com.box.sdkgen.managers.shuttle;

public class GetShuttleMappingErrorsV2026R0QueryParams {

  /** The unique identifier of the shuttle job whose mapping errors to list. */
  public final String jobId;

  /**
   * Defines the position marker at which to begin returning results. This is used when paginating
   * using marker-based pagination.
   *
   * <p>This requires `usemarker` to be set to `true`.
   */
  public String marker;

  /** The maximum number of items to return per page. */
  public Long limit;

  public GetShuttleMappingErrorsV2026R0QueryParams(String jobId) {
    this.jobId = jobId;
  }

  protected GetShuttleMappingErrorsV2026R0QueryParams(Builder builder) {
    this.jobId = builder.jobId;
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public String getJobId() {
    return jobId;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class Builder {

    protected final String jobId;

    protected String marker;

    protected Long limit;

    public Builder(String jobId) {
      this.jobId = jobId;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetShuttleMappingErrorsV2026R0QueryParams build() {
      return new GetShuttleMappingErrorsV2026R0QueryParams(this);
    }
  }
}
