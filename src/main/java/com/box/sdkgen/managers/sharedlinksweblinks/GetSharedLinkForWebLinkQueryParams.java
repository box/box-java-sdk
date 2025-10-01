package com.box.sdkgen.managers.sharedlinksweblinks;

public class GetSharedLinkForWebLinkQueryParams {

  /** Explicitly request the `shared_link` fields to be returned for this item. */
  public final String fields;

  public GetSharedLinkForWebLinkQueryParams(String fields) {
    this.fields = fields;
  }

  public String getFields() {
    return fields;
  }
}
