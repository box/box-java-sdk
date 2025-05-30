package com.box.sdkgen.managers.listcollaborations;

public class GetGroupCollaborationsQueryParams {

  public Long limit;

  public Long offset;

  public GetGroupCollaborationsQueryParams() {}

  protected GetGroupCollaborationsQueryParams(GetGroupCollaborationsQueryParamsBuilder builder) {
    this.limit = builder.limit;
    this.offset = builder.offset;
  }

  public Long getLimit() {
    return limit;
  }

  public Long getOffset() {
    return offset;
  }

  public static class GetGroupCollaborationsQueryParamsBuilder {

    protected Long limit;

    protected Long offset;

    public GetGroupCollaborationsQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetGroupCollaborationsQueryParamsBuilder offset(Long offset) {
      this.offset = offset;
      return this;
    }

    public GetGroupCollaborationsQueryParams build() {
      return new GetGroupCollaborationsQueryParams(this);
    }
  }
}
