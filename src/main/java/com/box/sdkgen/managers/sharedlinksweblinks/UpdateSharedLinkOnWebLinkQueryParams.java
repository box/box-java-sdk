package com.box.sdkgen.managers.sharedlinksweblinks;

public class UpdateSharedLinkOnWebLinkQueryParams {

  /** Explicitly request the `shared_link` fields to be returned for this item. */
  public final String fields;

  public UpdateSharedLinkOnWebLinkQueryParams(String fields) {
    this.fields = fields;
  }

  public String getFields() {
    return fields;
  }
}
