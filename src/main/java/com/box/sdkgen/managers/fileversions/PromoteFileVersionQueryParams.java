package com.box.sdkgen.managers.fileversions;

import java.util.List;

public class PromoteFileVersionQueryParams {

  public List<String> fields;

  public PromoteFileVersionQueryParams() {}

  protected PromoteFileVersionQueryParams(Builder builder) {
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

    public PromoteFileVersionQueryParams build() {
      return new PromoteFileVersionQueryParams(this);
    }
  }
}
