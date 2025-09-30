package com.box.sdkgen.managers.groups;

import java.util.List;

public class CreateGroupQueryParams {

  public List<String> fields;

  public CreateGroupQueryParams() {}

  protected CreateGroupQueryParams(Builder builder) {
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

    public CreateGroupQueryParams build() {
      return new CreateGroupQueryParams(this);
    }
  }
}
