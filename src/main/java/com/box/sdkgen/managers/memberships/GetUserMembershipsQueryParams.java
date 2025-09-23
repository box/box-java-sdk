package com.box.sdkgen.managers.memberships;

public class GetUserMembershipsQueryParams {

  public Long limit;

  public Long offset;

  public GetUserMembershipsQueryParams() {}

  protected GetUserMembershipsQueryParams(Builder builder) {
    this.limit = builder.limit;
    this.offset = builder.offset;
  }

  public Long getLimit() {
    return limit;
  }

  public Long getOffset() {
    return offset;
  }

  public static class Builder {

    protected Long limit;

    protected Long offset;

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Builder offset(Long offset) {
      this.offset = offset;
      return this;
    }

    public GetUserMembershipsQueryParams build() {
      return new GetUserMembershipsQueryParams(this);
    }
  }
}
