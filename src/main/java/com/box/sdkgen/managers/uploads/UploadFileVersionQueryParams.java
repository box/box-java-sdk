package com.box.sdkgen.managers.uploads;

import java.util.List;

public class UploadFileVersionQueryParams {

  public List<String> fields;

  public UploadFileVersionQueryParams() {}

  protected UploadFileVersionQueryParams(Builder builder) {
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

    public UploadFileVersionQueryParams build() {
      return new UploadFileVersionQueryParams(this);
    }
  }
}
