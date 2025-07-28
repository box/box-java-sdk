package com.box.sdkgen.managers.files;

import java.util.List;

public class CopyFileQueryParams {

  public List<String> fields;

  public CopyFileQueryParams() {}

  protected CopyFileQueryParams(Builder builder) {
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

    public CopyFileQueryParams build() {
      return new CopyFileQueryParams(this);
    }
  }
}
