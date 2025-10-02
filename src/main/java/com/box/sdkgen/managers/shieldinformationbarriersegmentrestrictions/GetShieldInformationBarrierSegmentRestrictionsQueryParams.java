package com.box.sdkgen.managers.shieldinformationbarriersegmentrestrictions;

public class GetShieldInformationBarrierSegmentRestrictionsQueryParams {

  /** The ID of the shield information barrier segment. */
  public final String shieldInformationBarrierSegmentId;

  /**
   * Defines the position marker at which to begin returning results. This is used when paginating
   * using marker-based pagination.
   *
   * <p>This requires `usemarker` to be set to `true`.
   */
  public String marker;

  /** The maximum number of items to return per page. */
  public Long limit;

  public GetShieldInformationBarrierSegmentRestrictionsQueryParams(
      String shieldInformationBarrierSegmentId) {
    this.shieldInformationBarrierSegmentId = shieldInformationBarrierSegmentId;
  }

  protected GetShieldInformationBarrierSegmentRestrictionsQueryParams(Builder builder) {
    this.shieldInformationBarrierSegmentId = builder.shieldInformationBarrierSegmentId;
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public String getShieldInformationBarrierSegmentId() {
    return shieldInformationBarrierSegmentId;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class Builder {

    protected final String shieldInformationBarrierSegmentId;

    protected String marker;

    protected Long limit;

    public Builder(String shieldInformationBarrierSegmentId) {
      this.shieldInformationBarrierSegmentId = shieldInformationBarrierSegmentId;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetShieldInformationBarrierSegmentRestrictionsQueryParams build() {
      return new GetShieldInformationBarrierSegmentRestrictionsQueryParams(this);
    }
  }
}
