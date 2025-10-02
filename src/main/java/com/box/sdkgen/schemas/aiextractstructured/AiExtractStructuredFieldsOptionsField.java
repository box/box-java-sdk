package com.box.sdkgen.schemas.aiextractstructured;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class AiExtractStructuredFieldsOptionsField extends SerializableObject {

  /** A unique identifier for the field. */
  protected final String key;

  public AiExtractStructuredFieldsOptionsField(@JsonProperty("key") String key) {
    super();
    this.key = key;
  }

  public String getKey() {
    return key;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiExtractStructuredFieldsOptionsField casted = (AiExtractStructuredFieldsOptionsField) o;
    return Objects.equals(key, casted.key);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key);
  }

  @Override
  public String toString() {
    return "AiExtractStructuredFieldsOptionsField{" + "key='" + key + '\'' + "}";
  }
}
