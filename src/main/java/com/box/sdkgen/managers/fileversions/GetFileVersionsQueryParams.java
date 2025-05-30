package com.box.sdkgen.managers.fileversions;

import java.util.List;

public class GetFileVersionsQueryParams {

  public List<String> fields;

  public Long limit;

  public Long offset;

  public GetFileVersionsQueryParams() {}

  protected GetFileVersionsQueryParams(GetFileVersionsQueryParamsBuilder builder) {
    this.fields = builder.fields;
    this.limit = builder.limit;
    this.offset = builder.offset;
  }

  public List<String> getFields() {
    return fields;
  }

  public Long getLimit() {
    return limit;
  }

  public Long getOffset() {
    return offset;
  }

  public static class GetFileVersionsQueryParamsBuilder {

    protected List<String> fields;

    protected Long limit;

    protected Long offset;

    public GetFileVersionsQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public GetFileVersionsQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetFileVersionsQueryParamsBuilder offset(Long offset) {
      this.offset = offset;
      return this;
    }

    public GetFileVersionsQueryParams build() {
      return new GetFileVersionsQueryParams(this);
    }
  }
}
