package com.box.sdkgen.managers.shieldinformationbarriers;

public class GetShieldInformationBarriersQueryParams {

  public String marker;

  public Long limit;

  public GetShieldInformationBarriersQueryParams() {}

  protected GetShieldInformationBarriersQueryParams(
      GetShieldInformationBarriersQueryParamsBuilder builder) {
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class GetShieldInformationBarriersQueryParamsBuilder {

    protected String marker;

    protected Long limit;

    public GetShieldInformationBarriersQueryParamsBuilder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetShieldInformationBarriersQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetShieldInformationBarriersQueryParams build() {
      return new GetShieldInformationBarriersQueryParams(this);
    }
  }
}
