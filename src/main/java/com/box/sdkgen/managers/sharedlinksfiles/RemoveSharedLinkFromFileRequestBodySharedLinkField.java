package com.box.sdkgen.managers.sharedlinksfiles;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("nullablePropertyFilter")
public class RemoveSharedLinkFromFileRequestBodySharedLinkField extends SerializableObject {

  public RemoveSharedLinkFromFileRequestBodySharedLinkField() {
    super();
  }

  @Override
  public String toString() {
    return "RemoveSharedLinkFromFileRequestBodySharedLinkField{" + "}";
  }
}
