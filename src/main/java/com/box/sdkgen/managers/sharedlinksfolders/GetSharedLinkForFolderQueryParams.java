package com.box.sdkgen.managers.sharedlinksfolders;

public class GetSharedLinkForFolderQueryParams {

  /** Explicitly request the `shared_link` fields to be returned for this item. */
  public final String fields;

  public GetSharedLinkForFolderQueryParams(String fields) {
    this.fields = fields;
  }

  public String getFields() {
    return fields;
  }
}
