package com.box.sdkgen.schemas.v2025r0.archivev2025r0;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ArchiveV2025R0OwnedByField extends SerializableObject {

  /** The unique identifier that represents a user who owns the archive. */
  protected final String id;

  /** The value is always `user`. */
  protected final String type;

  public ArchiveV2025R0OwnedByField(
      @JsonProperty("id") String id, @JsonProperty("type") String type) {
    super();
    this.id = id;
    this.type = type;
  }

  public String getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ArchiveV2025R0OwnedByField casted = (ArchiveV2025R0OwnedByField) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "ArchiveV2025R0OwnedByField{" + "id='" + id + '\'' + ", " + "type='" + type + '\'' + "}";
  }
}
