package com.box.sdkgen.managers.archives;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateArchiveV2025R0RequestBody extends SerializableObject {

  protected final String name;

  public CreateArchiveV2025R0RequestBody(@JsonProperty("name") String name) {
    super();
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateArchiveV2025R0RequestBody casted = (CreateArchiveV2025R0RequestBody) o;
    return Objects.equals(name, casted.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    return "CreateArchiveV2025R0RequestBody{" + "name='" + name + '\'' + "}";
  }
}
