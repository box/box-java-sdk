package com.box.sdkgen.managers.devicepinners;

import com.box.sdkgen.serialization.json.EnumWrapper;

public class GetEnterpriseDevicePinnersQueryParams {

  public String marker;

  public Long limit;

  public EnumWrapper<GetEnterpriseDevicePinnersQueryParamsDirectionField> direction;

  public GetEnterpriseDevicePinnersQueryParams() {}

  protected GetEnterpriseDevicePinnersQueryParams(
      GetEnterpriseDevicePinnersQueryParamsBuilder builder) {
    this.marker = builder.marker;
    this.limit = builder.limit;
    this.direction = builder.direction;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public EnumWrapper<GetEnterpriseDevicePinnersQueryParamsDirectionField> getDirection() {
    return direction;
  }

  public static class GetEnterpriseDevicePinnersQueryParamsBuilder {

    protected String marker;

    protected Long limit;

    protected EnumWrapper<GetEnterpriseDevicePinnersQueryParamsDirectionField> direction;

    public GetEnterpriseDevicePinnersQueryParamsBuilder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetEnterpriseDevicePinnersQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetEnterpriseDevicePinnersQueryParamsBuilder direction(
        GetEnterpriseDevicePinnersQueryParamsDirectionField direction) {
      this.direction =
          new EnumWrapper<GetEnterpriseDevicePinnersQueryParamsDirectionField>(direction);
      return this;
    }

    public GetEnterpriseDevicePinnersQueryParamsBuilder direction(
        EnumWrapper<GetEnterpriseDevicePinnersQueryParamsDirectionField> direction) {
      this.direction = direction;
      return this;
    }

    public GetEnterpriseDevicePinnersQueryParams build() {
      return new GetEnterpriseDevicePinnersQueryParams(this);
    }
  }
}
