package com.box.sdkgen.managers.sharedlinksfolders;

import java.util.List;

public class FindFolderForSharedLinkQueryParams {

  public List<String> fields;

  public FindFolderForSharedLinkQueryParams() {}

  protected FindFolderForSharedLinkQueryParams(FindFolderForSharedLinkQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class FindFolderForSharedLinkQueryParamsBuilder {

    protected List<String> fields;

    public FindFolderForSharedLinkQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public FindFolderForSharedLinkQueryParams build() {
      return new FindFolderForSharedLinkQueryParams(this);
    }
  }
}
