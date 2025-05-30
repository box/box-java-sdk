package com.box.sdkgen.managers.sharedlinksweblinks;

import java.util.List;

public class FindWebLinkForSharedLinkQueryParams {

  public List<String> fields;

  public FindWebLinkForSharedLinkQueryParams() {}

  protected FindWebLinkForSharedLinkQueryParams(
      FindWebLinkForSharedLinkQueryParamsBuilder builder) {
    this.fields = builder.fields;
  }

  public List<String> getFields() {
    return fields;
  }

  public static class FindWebLinkForSharedLinkQueryParamsBuilder {

    protected List<String> fields;

    public FindWebLinkForSharedLinkQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public FindWebLinkForSharedLinkQueryParams build() {
      return new FindWebLinkForSharedLinkQueryParams(this);
    }
  }
}
