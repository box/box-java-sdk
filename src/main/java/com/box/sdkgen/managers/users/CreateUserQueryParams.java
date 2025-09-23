package com.box.sdkgen.managers.users;

import java.util.List;

public class CreateUserQueryParams {

  public List<String> fields;

  public CreateUserQueryParams() {}

  protected CreateUserQueryParams(Builder builder) {
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

    public CreateUserQueryParams build() {
      return new CreateUserQueryParams(this);
    }
  }
}
