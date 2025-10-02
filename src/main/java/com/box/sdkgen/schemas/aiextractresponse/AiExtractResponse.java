package com.box.sdkgen.schemas.aiextractresponse;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;

/**
 * AI extract response. The content of this response may vary depending on the requested
 * configuration.
 */
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
