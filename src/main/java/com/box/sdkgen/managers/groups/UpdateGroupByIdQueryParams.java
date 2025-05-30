package com.box.sdkgen.managers.groups;

import java.util.List;

public class UpdateGroupByIdQueryParams {

  public List<String> fields;

  public UpdateGroupByIdQueryParams() {}

  protected UpdateGroupByIdQueryParams(UpdateGroupByIdQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class UpdateGroupByIdQueryParamsBuilder {

    protected List<String> fields;

    public UpdateGroupByIdQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public UpdateGroupByIdQueryParams build() {
      return new UpdateGroupByIdQueryParams(this);
    }
  }
}
