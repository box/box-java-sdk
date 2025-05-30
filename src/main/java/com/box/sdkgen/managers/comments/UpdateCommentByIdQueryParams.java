package com.box.sdkgen.managers.comments;

import java.util.List;

public class UpdateCommentByIdQueryParams {

  public List<String> fields;

  public UpdateCommentByIdQueryParams() {}

  protected UpdateCommentByIdQueryParams(UpdateCommentByIdQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class UpdateCommentByIdQueryParamsBuilder {

    protected List<String> fields;

    public UpdateCommentByIdQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public UpdateCommentByIdQueryParams build() {
      return new UpdateCommentByIdQueryParams(this);
    }
  }
}
