package com.box.sdkgen.managers.folderlocks;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateFolderLockRequestBodyFolderField extends SerializableObject {

  /** The content type the lock is being applied to. Only `folder` is supported. */
  protected final String type;

  /** The ID of the folder. */
  protected final String id;

  public CreateFolderLockRequestBodyFolderField(
      @JsonProperty("type") String type, @JsonProperty("id") String id) {
    super();
    this.type = type;
    this.id = id;
  }

  public String getType() {
    return type;
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
    CreateFolderLockRequestBodyFolderField casted = (CreateFolderLockRequestBodyFolderField) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "CreateFolderLockRequestBodyFolderField{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + "}";
  }
}
