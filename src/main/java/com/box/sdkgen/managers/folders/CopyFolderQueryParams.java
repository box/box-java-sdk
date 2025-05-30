package com.box.sdkgen.managers.folders;

import java.util.List;

public class CopyFolderQueryParams {

  public List<String> fields;

  public CopyFolderQueryParams() {}

  protected CopyFolderQueryParams(CopyFolderQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class CopyFolderQueryParamsBuilder {

    protected List<String> fields;

    public CopyFolderQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public CopyFolderQueryParams build() {
      return new CopyFolderQueryParams(this);
    }
  }
}
