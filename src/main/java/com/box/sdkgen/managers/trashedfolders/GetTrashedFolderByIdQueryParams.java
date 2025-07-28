package com.box.sdkgen.managers.trashedfolders;

import java.util.List;

public class GetTrashedFolderByIdQueryParams {

  public List<String> fields;

  public GetTrashedFolderByIdQueryParams() {}

  protected GetTrashedFolderByIdQueryParams(Builder builder) {
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

    public GetTrashedFolderByIdQueryParams build() {
      return new GetTrashedFolderByIdQueryParams(this);
    }
  }
}
