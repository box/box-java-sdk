package com.box.sdkgen.managers.trashedfiles;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class RestoreFileFromTrashRequestBody extends SerializableObject {

  protected String name;

  protected RestoreFileFromTrashRequestBodyParentField parent;

  public RestoreFileFromTrashRequestBody() {
    super();
  }

  protected RestoreFileFromTrashRequestBody(Builder builder) {
    super();
    this.name = builder.name;
    this.parent = builder.parent;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getName() {
    return name;
  }

  public RestoreFileFromTrashRequestBodyParentField getParent() {
    return parent;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RestoreFileFromTrashRequestBody casted = (RestoreFileFromTrashRequestBody) o;
    return Objects.equals(name, casted.name) && Objects.equals(parent, casted.parent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, parent);
  }

  @Override
  public String toString() {
    return "RestoreFileFromTrashRequestBody{"
        + "name='"
        + name
        + '\''
        + ", "
        + "parent='"
        + parent
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String name;

    protected RestoreFileFromTrashRequestBodyParentField parent;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder parent(RestoreFileFromTrashRequestBodyParentField parent) {
      this.parent = parent;
      return this;
    }

    public RestoreFileFromTrashRequestBody build() {
      return new RestoreFileFromTrashRequestBody(this);
    }
  }
}
