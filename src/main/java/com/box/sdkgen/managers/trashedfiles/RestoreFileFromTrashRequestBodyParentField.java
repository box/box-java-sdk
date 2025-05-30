package com.box.sdkgen.managers.trashedfiles;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class RestoreFileFromTrashRequestBodyParentField extends SerializableObject {

  protected String id;

  public RestoreFileFromTrashRequestBodyParentField() {
    super();
  }

  protected RestoreFileFromTrashRequestBodyParentField(
      RestoreFileFromTrashRequestBodyParentFieldBuilder builder) {
    super();
    this.id = builder.id;
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

  public static class RestoreFileFromTrashRequestBodyParentFieldBuilder {

    protected String id;

    public RestoreFileFromTrashRequestBodyParentFieldBuilder id(String id) {
      this.id = id;
      return this;
    }

    public RestoreFileFromTrashRequestBodyParentField build() {
      return new RestoreFileFromTrashRequestBodyParentField(this);
    }
  }
}
