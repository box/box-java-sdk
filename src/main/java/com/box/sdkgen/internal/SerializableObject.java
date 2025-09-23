package com.box.sdkgen.internal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.Serializable;

public class SerializableObject extends NullableFieldTracker implements Serializable {

  @JsonIgnore private JsonNode rawData;

  public JsonNode getRawData() {
    return rawData;
  }

  public void setRawData(JsonNode rawData) {
    this.rawData = rawData;
  }
}
