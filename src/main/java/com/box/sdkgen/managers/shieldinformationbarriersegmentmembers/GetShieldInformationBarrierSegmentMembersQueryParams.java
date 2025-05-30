package com.box.sdkgen.managers.shieldinformationbarriersegmentmembers;

public class GetShieldInformationBarrierSegmentMembersQueryParams {

  public final String shieldInformationBarrierSegmentId;

  public String marker;

  public Long limit;

  public GetShieldInformationBarrierSegmentMembersQueryParams(
      String shieldInformationBarrierSegmentId) {
    this.shieldInformationBarrierSegmentId = shieldInformationBarrierSegmentId;
  }

  protected GetShieldInformationBarrierSegmentMembersQueryParams(
      GetShieldInformationBarrierSegmentMembersQueryParamsBuilder builder) {
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

  public static class GetShieldInformationBarrierSegmentMembersQueryParamsBuilder {

    protected final String shieldInformationBarrierSegmentId;

    protected String marker;

    protected Long limit;

    public GetShieldInformationBarrierSegmentMembersQueryParamsBuilder(
        String shieldInformationBarrierSegmentId) {
      this.shieldInformationBarrierSegmentId = shieldInformationBarrierSegmentId;
    }

    public GetShieldInformationBarrierSegmentMembersQueryParamsBuilder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetShieldInformationBarrierSegmentMembersQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetShieldInformationBarrierSegmentMembersQueryParams build() {
      return new GetShieldInformationBarrierSegmentMembersQueryParams(this);
    }
  }
}
