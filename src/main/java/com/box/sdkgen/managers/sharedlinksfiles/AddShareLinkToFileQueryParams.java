package com.box.sdkgen.managers.sharedlinksfiles;

public class AddShareLinkToFileQueryParams {

  /** Explicitly request the `shared_link` fields to be returned for this item. */
  public final String fields;

  public AddShareLinkToFileQueryParams(String fields) {
    this.fields = fields;
  }

  public String getFields() {
    return fields;
  }
}
