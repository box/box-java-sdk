package com.box.sdkgen.managers.users;

import java.util.List;

public class GetUserMeQueryParams {

  public List<String> fields;

  public GetUserMeQueryParams() {}

  protected GetUserMeQueryParams(GetUserMeQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class GetUserMeQueryParamsBuilder {

    protected List<String> fields;

    public GetUserMeQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public GetUserMeQueryParams build() {
      return new GetUserMeQueryParams(this);
    }
  }
}
