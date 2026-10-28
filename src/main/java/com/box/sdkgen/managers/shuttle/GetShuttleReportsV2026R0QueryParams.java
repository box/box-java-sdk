package com.box.sdkgen.managers.shuttle;

public class GetShuttleReportsV2026R0QueryParams {

  /** The unique identifier of the shuttle run whose reports to list. */
  public final String runId;

  /**
   * Defines the position marker at which to begin returning results. This is used when paginating
   * using marker-based pagination.
   *
   * <p>This requires `usemarker` to be set to `true`.
   */
  public String marker;

  /** The maximum number of items to return per page. */
  public Long limit;

  public GetShuttleReportsV2026R0QueryParams(String runId) {
    this.runId = runId;
  }

  protected GetShuttleReportsV2026R0QueryParams(Builder builder) {
    this.runId = builder.runId;
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public String getRunId() {
    return runId;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class Builder {

    protected final String runId;

    protected String marker;

    protected Long limit;

    public Builder(String runId) {
      this.runId = runId;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetShuttleReportsV2026R0QueryParams build() {
      return new GetShuttleReportsV2026R0QueryParams(this);
    }
  }
}
