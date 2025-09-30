package com.box.sdkgen.managers.trashedfiles;

import java.util.List;

public class GetTrashedFileByIdQueryParams {

  public List<String> fields;

  public GetTrashedFileByIdQueryParams() {}

  protected GetTrashedFileByIdQueryParams(Builder builder) {
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

    public GetTrashedFileByIdQueryParams build() {
      return new GetTrashedFileByIdQueryParams(this);
    }
  }
}
