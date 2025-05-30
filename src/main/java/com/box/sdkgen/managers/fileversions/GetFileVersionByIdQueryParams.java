package com.box.sdkgen.managers.fileversions;

import java.util.List;

public class GetFileVersionByIdQueryParams {

  public List<String> fields;

  public GetFileVersionByIdQueryParams() {}

  protected GetFileVersionByIdQueryParams(GetFileVersionByIdQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class GetFileVersionByIdQueryParamsBuilder {

    protected List<String> fields;

    public GetFileVersionByIdQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public GetFileVersionByIdQueryParams build() {
      return new GetFileVersionByIdQueryParams(this);
    }
  }
}
