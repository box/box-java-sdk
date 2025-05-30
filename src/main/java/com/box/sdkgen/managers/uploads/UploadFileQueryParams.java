package com.box.sdkgen.managers.uploads;

import java.util.List;

public class UploadFileQueryParams {

  public List<String> fields;

  public UploadFileQueryParams() {}

  protected UploadFileQueryParams(UploadFileQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class UploadFileQueryParamsBuilder {

    protected List<String> fields;

    public UploadFileQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public UploadFileQueryParams build() {
      return new UploadFileQueryParams(this);
    }
  }
}
