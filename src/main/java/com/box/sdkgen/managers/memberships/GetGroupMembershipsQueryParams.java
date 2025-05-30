package com.box.sdkgen.managers.memberships;

public class GetGroupMembershipsQueryParams {

  public Long limit;

  public Long offset;

  public GetGroupMembershipsQueryParams() {}

  protected GetGroupMembershipsQueryParams(GetGroupMembershipsQueryParamsBuilder builder) {
    this.limit = builder.limit;
    this.offset = builder.offset;
  }

  public Long getLimit() {
    return limit;
  }

  public Long getOffset() {
    return offset;
  }

  public static class GetGroupMembershipsQueryParamsBuilder {

    protected Long limit;

    protected Long offset;

    public GetGroupMembershipsQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetGroupMembershipsQueryParamsBuilder offset(Long offset) {
      this.offset = offset;
      return this;
    }

    public GetGroupMembershipsQueryParams build() {
      return new GetGroupMembershipsQueryParams(this);
    }
  }
}
