package com.box.sdkgen.managers.sharedlinksweblinks;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("nullablePropertyFilter")
public class RemoveSharedLinkFromWebLinkRequestBodySharedLinkField extends SerializableObject {

  public RemoveSharedLinkFromWebLinkRequestBodySharedLinkField() {
    super();
  }

  @Override
  public String toString() {
    return "RemoveSharedLinkFromWebLinkRequestBodySharedLinkField{" + "}";
  }
}
