package com.box.sdkgen.managers.users;

import java.util.List;

public class GetUserByIdQueryParams {

  public List<String> fields;

  public GetUserByIdQueryParams() {}

  protected GetUserByIdQueryParams(GetUserByIdQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class GetUserByIdQueryParamsBuilder {

    protected List<String> fields;

    public GetUserByIdQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public GetUserByIdQueryParams build() {
      return new GetUserByIdQueryParams(this);
    }
  }
}
