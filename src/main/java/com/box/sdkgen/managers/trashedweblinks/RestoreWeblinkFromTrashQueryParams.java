package com.box.sdkgen.managers.trashedweblinks;

import java.util.List;

public class RestoreWeblinkFromTrashQueryParams {

  public List<String> fields;

  public RestoreWeblinkFromTrashQueryParams() {}

  protected RestoreWeblinkFromTrashQueryParams(Builder builder) {
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

    public RestoreWeblinkFromTrashQueryParams build() {
      return new RestoreWeblinkFromTrashQueryParams(this);
    }
  }
}
