package com.box.sdkgen.managers.files;

import java.util.List;

public class GetFileByIdQueryParams {

  public List<String> fields;

  public GetFileByIdQueryParams() {}

  protected GetFileByIdQueryParams(GetFileByIdQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class GetFileByIdQueryParamsBuilder {

    protected List<String> fields;

    public GetFileByIdQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public GetFileByIdQueryParams build() {
      return new GetFileByIdQueryParams(this);
    }
  }
}
