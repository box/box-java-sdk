package com.box.sdkgen.managers.folders;

import java.util.List;

public class UpdateFolderByIdQueryParams {

  public List<String> fields;

  public UpdateFolderByIdQueryParams() {}

  protected UpdateFolderByIdQueryParams(Builder builder) {
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

    public UpdateFolderByIdQueryParams build() {
      return new UpdateFolderByIdQueryParams(this);
    }
  }
}
