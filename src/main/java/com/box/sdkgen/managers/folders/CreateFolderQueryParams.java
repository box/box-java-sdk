package com.box.sdkgen.managers.folders;

import java.util.List;

public class CreateFolderQueryParams {

  public List<String> fields;

  public CreateFolderQueryParams() {}

  protected CreateFolderQueryParams(CreateFolderQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class CreateFolderQueryParamsBuilder {

    protected List<String> fields;

    public CreateFolderQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public CreateFolderQueryParams build() {
      return new CreateFolderQueryParams(this);
    }
  }
}
