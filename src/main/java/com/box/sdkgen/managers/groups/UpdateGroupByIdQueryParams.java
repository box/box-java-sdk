package com.box.sdkgen.managers.groups;

import java.util.List;

public class UpdateGroupByIdQueryParams {

  public List<String> fields;

  public UpdateGroupByIdQueryParams() {}

  protected UpdateGroupByIdQueryParams(Builder builder) {
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

    public UpdateGroupByIdQueryParams build() {
      return new UpdateGroupByIdQueryParams(this);
    }
  }
}
