package com.box.sdkgen.managers.shieldinformationbarriersegmentrestrictions;

public class GetShieldInformationBarrierSegmentRestrictionsQueryParams {

  public final String shieldInformationBarrierSegmentId;

  public String marker;

  public Long limit;

  public GetShieldInformationBarrierSegmentRestrictionsQueryParams(
      String shieldInformationBarrierSegmentId) {
    this.shieldInformationBarrierSegmentId = shieldInformationBarrierSegmentId;
  }

  protected GetShieldInformationBarrierSegmentRestrictionsQueryParams(
      GetShieldInformationBarrierSegmentRestrictionsQueryParamsBuilder builder) {
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

  public static class GetShieldInformationBarrierSegmentRestrictionsQueryParamsBuilder {

    protected final String shieldInformationBarrierSegmentId;

    protected String marker;

    protected Long limit;

    public GetShieldInformationBarrierSegmentRestrictionsQueryParamsBuilder(
        String shieldInformationBarrierSegmentId) {
      this.shieldInformationBarrierSegmentId = shieldInformationBarrierSegmentId;
    }

    public GetShieldInformationBarrierSegmentRestrictionsQueryParamsBuilder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetShieldInformationBarrierSegmentRestrictionsQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetShieldInformationBarrierSegmentRestrictionsQueryParams build() {
      return new GetShieldInformationBarrierSegmentRestrictionsQueryParams(this);
    }
  }
}
