package com.box.sdkgen.managers.comments;

import java.util.List;

public class CreateCommentQueryParams {

  public List<String> fields;

  public CreateCommentQueryParams() {}

  protected CreateCommentQueryParams(CreateCommentQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class CreateCommentQueryParamsBuilder {

    protected List<String> fields;

    public CreateCommentQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public CreateCommentQueryParams build() {
      return new CreateCommentQueryParams(this);
    }
  }
}
