package com.box.sdkgen.managers.trashedfolders;

import java.util.List;

public class RestoreFolderFromTrashQueryParams {

  public List<String> fields;

  public RestoreFolderFromTrashQueryParams() {}

  protected RestoreFolderFromTrashQueryParams(RestoreFolderFromTrashQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class RestoreFolderFromTrashQueryParamsBuilder {

    protected List<String> fields;

    public RestoreFolderFromTrashQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public RestoreFolderFromTrashQueryParams build() {
      return new RestoreFolderFromTrashQueryParams(this);
    }
  }
}
