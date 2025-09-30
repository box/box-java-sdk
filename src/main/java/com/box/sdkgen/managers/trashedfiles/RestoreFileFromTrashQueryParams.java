package com.box.sdkgen.managers.trashedfiles;

import java.util.List;

public class RestoreFileFromTrashQueryParams {

  public List<String> fields;

  public RestoreFileFromTrashQueryParams() {}

  protected RestoreFileFromTrashQueryParams(Builder builder) {
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

    public RestoreFileFromTrashQueryParams build() {
      return new RestoreFileFromTrashQueryParams(this);
    }
  }
}
