package com.box.sdkgen.schemas.aiextractresponse;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("nullablePropertyFilter")
public class AiExtractResponse extends SerializableObject {

  public AiExtractResponse() {
    super();
  }

  @Override
  public String toString() {
    return "AiExtractResponse{" + "}";
  }
}
