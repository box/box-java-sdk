package com.box.sdkgen.managers.sharedlinksfolders;

public class UpdateSharedLinkOnFolderQueryParams {

  /** Explicitly request the `shared_link` fields to be returned for this item. */
  public final String fields;

  public UpdateSharedLinkOnFolderQueryParams(String fields) {
    this.fields = fields;
  }

  public String getFields() {
    return fields;
  }
}
