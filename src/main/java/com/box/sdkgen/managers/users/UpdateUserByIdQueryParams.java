package com.box.sdkgen.managers.users;

import java.util.List;

public class UpdateUserByIdQueryParams {

  public List<String> fields;

  public UpdateUserByIdQueryParams() {}

  protected UpdateUserByIdQueryParams(UpdateUserByIdQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class UpdateUserByIdQueryParamsBuilder {

    protected List<String> fields;

    public UpdateUserByIdQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public UpdateUserByIdQueryParams build() {
      return new UpdateUserByIdQueryParams(this);
    }
  }
}
