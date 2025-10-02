package com.box.sdkgen.managers.sharedlinksfiles;

public class RemoveSharedLinkFromFileQueryParams {

  /** Explicitly request the `shared_link` fields to be returned for this item. */
  public final String fields;

  public RemoveSharedLinkFromFileQueryParams(String fields) {
    this.fields = fields;
  }

  public String getFields() {
    return fields;
  }
}
