package com.box.sdkgen.managers.users;

import java.util.List;

public class UpdateUserByIdQueryParams {

  public List<String> fields;

  public UpdateUserByIdQueryParams() {}

  protected UpdateUserByIdQueryParams(Builder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class Builder {

    protected List<String> fields;

    public Builder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public UpdateUserByIdQueryParams build() {
      return new UpdateUserByIdQueryParams(this);
    }
  }
}
