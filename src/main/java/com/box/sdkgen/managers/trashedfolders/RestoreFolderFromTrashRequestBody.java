package com.box.sdkgen.managers.trashedfolders;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class RestoreFolderFromTrashRequestBody extends SerializableObject {

  protected String name;

  protected RestoreFolderFromTrashRequestBodyParentField parent;

  public RestoreFolderFromTrashRequestBody() {
    super();
  }

  protected RestoreFolderFromTrashRequestBody(Builder builder) {
    super();
    this.name = builder.name;
    this.parent = builder.parent;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getName() {
    return name;
  }

  public RestoreFolderFromTrashRequestBodyParentField getParent() {
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
    RestoreFolderFromTrashRequestBody casted = (RestoreFolderFromTrashRequestBody) o;
    return Objects.equals(name, casted.name) && Objects.equals(parent, casted.parent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, parent);
  }

  @Override
  public String toString() {
    return "RestoreFolderFromTrashRequestBody{"
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

    protected RestoreFolderFromTrashRequestBodyParentField parent;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder parent(RestoreFolderFromTrashRequestBodyParentField parent) {
      this.parent = parent;
      return this;
    }

    public RestoreFolderFromTrashRequestBody build() {
      return new RestoreFolderFromTrashRequestBody(this);
    }
  }
}
