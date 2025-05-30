package com.box.sdkgen.managers.trashedfolders;

import java.util.List;

public class GetTrashedFolderByIdQueryParams {

  public List<String> fields;

  public GetTrashedFolderByIdQueryParams() {}

  protected GetTrashedFolderByIdQueryParams(GetTrashedFolderByIdQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class GetTrashedFolderByIdQueryParamsBuilder {

    protected List<String> fields;

    public GetTrashedFolderByIdQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public GetTrashedFolderByIdQueryParams build() {
      return new GetTrashedFolderByIdQueryParams(this);
    }
  }
}
