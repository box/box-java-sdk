package com.box.sdkgen.managers.sharedlinksfiles;

import java.util.List;

public class FindFileForSharedLinkQueryParams {

  public List<String> fields;

  public FindFileForSharedLinkQueryParams() {}

  protected FindFileForSharedLinkQueryParams(Builder builder) {
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

    public FindFileForSharedLinkQueryParams build() {
      return new FindFileForSharedLinkQueryParams(this);
    }
  }
}
