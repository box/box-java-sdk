package com.box.sdkgen.managers.usercollaborations;

import java.util.List;

public class GetCollaborationByIdQueryParams {

  public List<String> fields;

  public GetCollaborationByIdQueryParams() {}

  protected GetCollaborationByIdQueryParams(GetCollaborationByIdQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class GetCollaborationByIdQueryParamsBuilder {

    protected List<String> fields;

    public GetCollaborationByIdQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public GetCollaborationByIdQueryParams build() {
      return new GetCollaborationByIdQueryParams(this);
    }
  }
}
