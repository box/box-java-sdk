package com.box.sdkgen.managers.sharedlinksweblinks;

import java.util.List;

public class FindWebLinkForSharedLinkQueryParams {

  public List<String> fields;

  public FindWebLinkForSharedLinkQueryParams() {}

  protected FindWebLinkForSharedLinkQueryParams(Builder builder) {
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

    public FindWebLinkForSharedLinkQueryParams build() {
      return new FindWebLinkForSharedLinkQueryParams(this);
    }
  }
}
