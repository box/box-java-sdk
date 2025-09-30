package com.box.sdkgen.managers.usercollaborations;

import java.util.List;

public class GetCollaborationByIdQueryParams {

  public List<String> fields;

  public GetCollaborationByIdQueryParams() {}

  protected GetCollaborationByIdQueryParams(Builder builder) {
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

    public GetCollaborationByIdQueryParams build() {
      return new GetCollaborationByIdQueryParams(this);
    }
  }
}
