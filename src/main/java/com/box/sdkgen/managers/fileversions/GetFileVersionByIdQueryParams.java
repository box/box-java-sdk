package com.box.sdkgen.managers.fileversions;

import java.util.List;

public class GetFileVersionByIdQueryParams {

  public List<String> fields;

  public GetFileVersionByIdQueryParams() {}

  protected GetFileVersionByIdQueryParams(Builder builder) {
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

    public GetFileVersionByIdQueryParams build() {
      return new GetFileVersionByIdQueryParams(this);
    }
  }
}
