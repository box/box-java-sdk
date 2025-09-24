package com.box.sdkgen.managers.uploads;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UploadWithPreflightCheckRequestBodyAttributesParentField extends SerializableObject {

  protected final String id;

  public UploadWithPreflightCheckRequestBodyAttributesParentField(@JsonProperty("id") String id) {
    super();
    this.id = id;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UploadWithPreflightCheckRequestBodyAttributesParentField casted =
        (UploadWithPreflightCheckRequestBodyAttributesParentField) o;
    return Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "UploadWithPreflightCheckRequestBodyAttributesParentField{" + "id='" + id + '\'' + "}";
  }
}
