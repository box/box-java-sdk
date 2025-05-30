package com.box.sdkgen.managers.memberships;

public class GetUserMembershipsQueryParams {

  public Long limit;

  public Long offset;

  public GetUserMembershipsQueryParams() {}

  protected GetUserMembershipsQueryParams(GetUserMembershipsQueryParamsBuilder builder) {
    this.limit = builder.limit;
    this.offset = builder.offset;
  }

  public Long getLimit() {
    return limit;
  }

  public Long getOffset() {
    return offset;
  }

  public static class GetUserMembershipsQueryParamsBuilder {

    protected Long limit;

    protected Long offset;

    public GetUserMembershipsQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetUserMembershipsQueryParamsBuilder offset(Long offset) {
      this.offset = offset;
      return this;
    }

    public GetUserMembershipsQueryParams build() {
      return new GetUserMembershipsQueryParams(this);
    }
  }
}
