package com.box.sdkgen.managers.comments;

import java.util.List;

public class GetCommentByIdQueryParams {

  public List<String> fields;

  public GetCommentByIdQueryParams() {}

  protected GetCommentByIdQueryParams(Builder builder) {
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

    public GetCommentByIdQueryParams build() {
      return new GetCommentByIdQueryParams(this);
    }
  }
}
