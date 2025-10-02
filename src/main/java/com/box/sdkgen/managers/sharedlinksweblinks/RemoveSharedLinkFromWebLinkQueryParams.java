package com.box.sdkgen.managers.sharedlinksweblinks;

public class RemoveSharedLinkFromWebLinkQueryParams {

  /** Explicitly request the `shared_link` fields to be returned for this item. */
  public final String fields;

  public RemoveSharedLinkFromWebLinkQueryParams(String fields) {
    this.fields = fields;
  }

  public String getFields() {
    return fields;
  }
}
