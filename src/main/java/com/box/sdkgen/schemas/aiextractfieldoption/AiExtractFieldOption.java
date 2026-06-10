package com.box.sdkgen.schemas.aiextractfieldoption;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** An option for an AI extract field. */
@JsonFilter("nullablePropertyFilter")
public class AiExtractFieldOption extends SerializableObject {

  /** A unique identifier for the option. */
  protected final String key;

  public AiExtractFieldOption(@JsonProperty("key") String key) {
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
    AiExtractFieldOption casted = (AiExtractFieldOption) o;
    return Objects.equals(key, casted.key);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key);
  }

  @Override
  public String toString() {
    return "AiExtractFieldOption{" + "key='" + key + '\'' + "}";
  }
}
