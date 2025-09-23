package com.box.sdkgen.managers.shieldinformationbarrierreports;

public class GetShieldInformationBarrierReportsQueryParams {

  public final String shieldInformationBarrierId;

  public String marker;

  public Long limit;

  public GetShieldInformationBarrierReportsQueryParams(String shieldInformationBarrierId) {
    this.shieldInformationBarrierId = shieldInformationBarrierId;
  }

  protected GetShieldInformationBarrierReportsQueryParams(Builder builder) {
    this.shieldInformationBarrierId = builder.shieldInformationBarrierId;
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public String getShieldInformationBarrierId() {
    return shieldInformationBarrierId;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class Builder {

    protected final String shieldInformationBarrierId;

    protected String marker;

    protected Long limit;

    public Builder(String shieldInformationBarrierId) {
      this.shieldInformationBarrierId = shieldInformationBarrierId;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetShieldInformationBarrierReportsQueryParams build() {
      return new GetShieldInformationBarrierReportsQueryParams(this);
    }
  }
}
