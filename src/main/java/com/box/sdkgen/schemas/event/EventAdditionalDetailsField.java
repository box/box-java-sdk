package com.box.sdkgen.schemas.event;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("nullablePropertyFilter")
public class EventAdditionalDetailsField extends SerializableObject {

  public EventAdditionalDetailsField() {
    super();
  }

  @Override
  public String toString() {
    return "EventAdditionalDetailsField{" + "}";
  }
}
