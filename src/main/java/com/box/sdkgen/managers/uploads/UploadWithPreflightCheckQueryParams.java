package com.box.sdkgen.managers.uploads;

import java.util.List;

public class UploadWithPreflightCheckQueryParams {

  public List<String> fields;

  public UploadWithPreflightCheckQueryParams() {}

  protected UploadWithPreflightCheckQueryParams(
      UploadWithPreflightCheckQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class UploadWithPreflightCheckQueryParamsBuilder {

    protected List<String> fields;

    public UploadWithPreflightCheckQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public UploadWithPreflightCheckQueryParams build() {
      return new UploadWithPreflightCheckQueryParams(this);
    }
  }
}
