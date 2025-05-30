package com.box.sdkgen.managers.uploads;

import java.util.List;

public class UploadFileVersionQueryParams {

  public List<String> fields;

  public UploadFileVersionQueryParams() {}

  protected UploadFileVersionQueryParams(UploadFileVersionQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class UploadFileVersionQueryParamsBuilder {

    protected List<String> fields;

    public UploadFileVersionQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public UploadFileVersionQueryParams build() {
      return new UploadFileVersionQueryParams(this);
    }
  }
}
