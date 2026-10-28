package com.box.sdkgen.managers.shuttle;

import com.box.sdkgen.serialization.json.EnumWrapper;

public class GetShuttlePathEntriesV2026R0QueryParams {

  /** The unique identifier of the shuttle job whose path entries to list. */
  public final String jobId;

  /** The side of the migration to browse. */
  public final EnumWrapper<GetShuttlePathEntriesV2026R0QueryParamsSideField> side;

  /**
   * The human-readable parent path whose children are returned. This can be used with or without
   * `path_id`. Omit both, or pass `/` as `path`, to list root entries.
   */
  public String path;

  /**
   * The `path_id` value of the parent entry whose children are returned. This can be used with or
   * without `path`. It distinguishes paths with identical names on systems that support stable
   * identifiers.
   */
  public String pathId;

  /**
   * The email of the account whose path entries are returned. This is a system-native identifier
   * and is not always an RFC 5322 address.
   */
  public String accountEmail;

  /**
   * Defines the position marker at which to begin returning results. This is used when paginating
   * using marker-based pagination.
   *
   * <p>This requires `usemarker` to be set to `true`.
   */
  public String marker;

  /** The maximum number of items to return per page. */
  public Long limit;

  public GetShuttlePathEntriesV2026R0QueryParams(
      String jobId, GetShuttlePathEntriesV2026R0QueryParamsSideField side) {
    this.jobId = jobId;
    this.side = new EnumWrapper<GetShuttlePathEntriesV2026R0QueryParamsSideField>(side);
  }

  public GetShuttlePathEntriesV2026R0QueryParams(
      String jobId, EnumWrapper<GetShuttlePathEntriesV2026R0QueryParamsSideField> side) {
    this.jobId = jobId;
    this.side = side;
  }

  protected GetShuttlePathEntriesV2026R0QueryParams(Builder builder) {
    this.jobId = builder.jobId;
    this.side = builder.side;
    this.path = builder.path;
    this.pathId = builder.pathId;
    this.accountEmail = builder.accountEmail;
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public String getJobId() {
    return jobId;
  }

  public EnumWrapper<GetShuttlePathEntriesV2026R0QueryParamsSideField> getSide() {
    return side;
  }

  public String getPath() {
    return path;
  }

  public String getPathId() {
    return pathId;
  }

  public String getAccountEmail() {
    return accountEmail;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class Builder {

    protected final String jobId;

    protected final EnumWrapper<GetShuttlePathEntriesV2026R0QueryParamsSideField> side;

    protected String path;

    protected String pathId;

    protected String accountEmail;

    protected String marker;

    protected Long limit;

    public Builder(String jobId, GetShuttlePathEntriesV2026R0QueryParamsSideField side) {
      this.jobId = jobId;
      this.side = new EnumWrapper<GetShuttlePathEntriesV2026R0QueryParamsSideField>(side);
    }

    public Builder(
        String jobId, EnumWrapper<GetShuttlePathEntriesV2026R0QueryParamsSideField> side) {
      this.jobId = jobId;
      this.side = side;
    }

    public Builder path(String path) {
      this.path = path;
      return this;
    }

    public Builder pathId(String pathId) {
      this.pathId = pathId;
      return this;
    }

    public Builder accountEmail(String accountEmail) {
      this.accountEmail = accountEmail;
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

    public GetShuttlePathEntriesV2026R0QueryParams build() {
      return new GetShuttlePathEntriesV2026R0QueryParams(this);
    }
  }
}
