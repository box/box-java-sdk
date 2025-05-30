package com.box.sdkgen.managers.trashedweblinks;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class RestoreWeblinkFromTrashRequestBodyParentField extends SerializableObject {

  protected String id;

  public RestoreWeblinkFromTrashRequestBodyParentField() {
    super();
  }

  protected RestoreWeblinkFromTrashRequestBodyParentField(
      RestoreWeblinkFromTrashRequestBodyParentFieldBuilder builder) {
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
    RestoreWeblinkFromTrashRequestBodyParentField casted =
        (RestoreWeblinkFromTrashRequestBodyParentField) o;
    return Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "RestoreWeblinkFromTrashRequestBodyParentField{" + "id='" + id + '\'' + "}";
  }

  public static class RestoreWeblinkFromTrashRequestBodyParentFieldBuilder {

    protected String id;

    public RestoreWeblinkFromTrashRequestBodyParentFieldBuilder id(String id) {
      this.id = id;
      return this;
    }

    public RestoreWeblinkFromTrashRequestBodyParentField build() {
      return new RestoreWeblinkFromTrashRequestBodyParentField(this);
    }
  }
}
