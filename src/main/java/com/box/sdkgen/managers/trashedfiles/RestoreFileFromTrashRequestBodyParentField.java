package com.box.sdkgen.managers.trashedfiles;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class RestoreFileFromTrashRequestBodyParentField extends SerializableObject {

  /** The ID of parent item. */
  protected String id;

  public RestoreFileFromTrashRequestBodyParentField() {
    super();
  }

  protected RestoreFileFromTrashRequestBodyParentField(Builder builder) {
    super();
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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
    RestoreFileFromTrashRequestBodyParentField casted =
        (RestoreFileFromTrashRequestBodyParentField) o;
    return Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "RestoreFileFromTrashRequestBodyParentField{" + "id='" + id + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public RestoreFileFromTrashRequestBodyParentField build() {
      return new RestoreFileFromTrashRequestBodyParentField(this);
    }
  }
}
