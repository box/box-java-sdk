package com.box.sdkgen.managers.devicepinners;

import com.box.sdkgen.serialization.json.EnumWrapper;

public class GetEnterpriseDevicePinnersQueryParams {

  /**
   * Defines the position marker at which to begin returning results. This is used when paginating
   * using marker-based pagination.
   *
   * <p>This requires `usemarker` to be set to `true`.
   */
  public String marker;

  /** The maximum number of items to return per page. */
  public Long limit;

  /**
   * The direction to sort results in. This can be either in alphabetical ascending (`ASC`) or
   * descending (`DESC`) order.
   */
  public EnumWrapper<GetEnterpriseDevicePinnersQueryParamsDirectionField> direction;

  public GetEnterpriseDevicePinnersQueryParams() {}

  protected GetEnterpriseDevicePinnersQueryParams(Builder builder) {
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

  public static class Builder {

    protected String marker;

    protected Long limit;

    protected EnumWrapper<GetEnterpriseDevicePinnersQueryParamsDirectionField> direction;

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Builder direction(GetEnterpriseDevicePinnersQueryParamsDirectionField direction) {
      this.direction =
          new EnumWrapper<GetEnterpriseDevicePinnersQueryParamsDirectionField>(direction);
      return this;
    }

    public Builder direction(
        EnumWrapper<GetEnterpriseDevicePinnersQueryParamsDirectionField> direction) {
      this.direction = direction;
      return this;
    }

    public GetEnterpriseDevicePinnersQueryParams build() {
      return new GetEnterpriseDevicePinnersQueryParams(this);
    }
  }
}
