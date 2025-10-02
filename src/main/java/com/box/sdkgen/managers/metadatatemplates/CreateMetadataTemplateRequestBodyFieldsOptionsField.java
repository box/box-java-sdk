package com.box.sdkgen.managers.metadatatemplates;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateMetadataTemplateRequestBodyFieldsOptionsField extends SerializableObject {

  /**
   * The text value of the option. This represents both the display name of the option and the
   * internal key used when updating templates.
   */
  protected final String key;

  public CreateMetadataTemplateRequestBodyFieldsOptionsField(@JsonProperty("key") String key) {
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
    CreateMetadataTemplateRequestBodyFieldsOptionsField casted =
        (CreateMetadataTemplateRequestBodyFieldsOptionsField) o;
    return Objects.equals(key, casted.key);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key);
  }

  @Override
  public String toString() {
    return "CreateMetadataTemplateRequestBodyFieldsOptionsField{" + "key='" + key + '\'' + "}";
  }
}
