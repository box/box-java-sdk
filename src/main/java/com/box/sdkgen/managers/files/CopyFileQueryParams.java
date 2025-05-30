package com.box.sdkgen.managers.files;

import java.util.List;

public class CopyFileQueryParams {

  public List<String> fields;

  public CopyFileQueryParams() {}

  protected CopyFileQueryParams(CopyFileQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class CopyFileQueryParamsBuilder {

    protected List<String> fields;

    public CopyFileQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public CopyFileQueryParams build() {
      return new CopyFileQueryParams(this);
    }
  }
}
