package com.box.sdkgen.managers.sharedlinksfolders;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("nullablePropertyFilter")
public class RemoveSharedLinkFromFolderRequestBodySharedLinkField extends SerializableObject {

  public RemoveSharedLinkFromFolderRequestBodySharedLinkField() {
    super();
  }

  @Override
  public String toString() {
    return "RemoveSharedLinkFromFolderRequestBodySharedLinkField{" + "}";
  }
}
