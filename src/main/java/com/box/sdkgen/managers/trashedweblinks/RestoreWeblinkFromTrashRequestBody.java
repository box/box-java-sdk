package com.box.sdkgen.managers.trashedweblinks;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class RestoreWeblinkFromTrashRequestBody extends SerializableObject {

  /** An optional new name for the web link. */
  protected String name;

  protected RestoreWeblinkFromTrashRequestBodyParentField parent;

  public RestoreWeblinkFromTrashRequestBody() {
    super();
  }

  protected RestoreWeblinkFromTrashRequestBody(Builder builder) {
    super();
    this.name = builder.name;
    this.parent = builder.parent;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getName() {
    return name;
  }

  public RestoreWeblinkFromTrashRequestBodyParentField getParent() {
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
    RestoreWeblinkFromTrashRequestBody casted = (RestoreWeblinkFromTrashRequestBody) o;
    return Objects.equals(name, casted.name) && Objects.equals(parent, casted.parent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, parent);
  }

  @Override
  public String toString() {
    return "RestoreWeblinkFromTrashRequestBody{"
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

    protected RestoreWeblinkFromTrashRequestBodyParentField parent;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder parent(RestoreWeblinkFromTrashRequestBodyParentField parent) {
      this.parent = parent;
      return this;
    }

    public RestoreWeblinkFromTrashRequestBody build() {
      return new RestoreWeblinkFromTrashRequestBody(this);
    }
  }
}
