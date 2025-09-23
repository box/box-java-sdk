package com.box.sdkgen.managers.files;

import java.util.List;

public class GetFileByIdQueryParams {

  public List<String> fields;

  public GetFileByIdQueryParams() {}

  protected GetFileByIdQueryParams(Builder builder) {
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

    public GetFileByIdQueryParams build() {
      return new GetFileByIdQueryParams(this);
    }
  }
}
