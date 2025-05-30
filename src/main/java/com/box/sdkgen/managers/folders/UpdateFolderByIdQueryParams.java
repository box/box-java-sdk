package com.box.sdkgen.managers.folders;

import java.util.List;

public class UpdateFolderByIdQueryParams {

  public List<String> fields;

  public UpdateFolderByIdQueryParams() {}

  protected UpdateFolderByIdQueryParams(UpdateFolderByIdQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class UpdateFolderByIdQueryParamsBuilder {

    protected List<String> fields;

    public UpdateFolderByIdQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public UpdateFolderByIdQueryParams build() {
      return new UpdateFolderByIdQueryParams(this);
    }
  }
}
