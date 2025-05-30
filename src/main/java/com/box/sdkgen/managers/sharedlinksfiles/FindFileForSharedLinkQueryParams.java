package com.box.sdkgen.managers.sharedlinksfiles;

import java.util.List;

public class FindFileForSharedLinkQueryParams {

  public List<String> fields;

  public FindFileForSharedLinkQueryParams() {}

  protected FindFileForSharedLinkQueryParams(FindFileForSharedLinkQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class FindFileForSharedLinkQueryParamsBuilder {

    protected List<String> fields;

    public FindFileForSharedLinkQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public FindFileForSharedLinkQueryParams build() {
      return new FindFileForSharedLinkQueryParams(this);
    }
  }
}
