package com.box.sdkgen.managers.memberships;

public class GetUserMembershipsQueryParams {

  /** The maximum number of items to return per page. */
  public Long limit;

  /**
   * The offset of the item at which to begin the response.
   *
   * <p>Queries with offset parameter value exceeding 10000 will be rejected with a 400 response.
   */
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
