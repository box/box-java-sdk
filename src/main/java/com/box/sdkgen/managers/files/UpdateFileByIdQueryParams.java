package com.box.sdkgen.managers.files;

import java.util.List;

public class UpdateFileByIdQueryParams {

  public List<String> fields;

  public UpdateFileByIdQueryParams() {}

  protected UpdateFileByIdQueryParams(Builder builder) {
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

    public UpdateFileByIdQueryParams build() {
      return new UpdateFileByIdQueryParams(this);
    }
  }
}
