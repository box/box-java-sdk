package com.box.sdkgen.managers.trashedweblinks;

import java.util.List;

public class RestoreWeblinkFromTrashQueryParams {

  public List<String> fields;

  public RestoreWeblinkFromTrashQueryParams() {}

  protected RestoreWeblinkFromTrashQueryParams(RestoreWeblinkFromTrashQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class RestoreWeblinkFromTrashQueryParamsBuilder {

    protected List<String> fields;

    public RestoreWeblinkFromTrashQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public RestoreWeblinkFromTrashQueryParams build() {
      return new RestoreWeblinkFromTrashQueryParams(this);
    }
  }
}
