package com.box.sdkgen.managers.groups;

import java.util.List;

public class CreateGroupQueryParams {

  public List<String> fields;

  public CreateGroupQueryParams() {}

  protected CreateGroupQueryParams(CreateGroupQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class CreateGroupQueryParamsBuilder {

    protected List<String> fields;

    public CreateGroupQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public CreateGroupQueryParams build() {
      return new CreateGroupQueryParams(this);
    }
  }
}
