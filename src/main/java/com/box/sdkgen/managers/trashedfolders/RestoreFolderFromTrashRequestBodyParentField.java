package com.box.sdkgen.managers.trashedfolders;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class RestoreFolderFromTrashRequestBodyParentField extends SerializableObject {

  protected String id;

  public RestoreFolderFromTrashRequestBodyParentField() {
    super();
  }

  protected RestoreFolderFromTrashRequestBodyParentField(
      RestoreFolderFromTrashRequestBodyParentFieldBuilder builder) {
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
    RestoreFolderFromTrashRequestBodyParentField casted =
        (RestoreFolderFromTrashRequestBodyParentField) o;
    return Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "RestoreFolderFromTrashRequestBodyParentField{" + "id='" + id + '\'' + "}";
  }

  public static class RestoreFolderFromTrashRequestBodyParentFieldBuilder {

    protected String id;

    public RestoreFolderFromTrashRequestBodyParentFieldBuilder id(String id) {
      this.id = id;
      return this;
    }

    public RestoreFolderFromTrashRequestBodyParentField build() {
      return new RestoreFolderFromTrashRequestBodyParentField(this);
    }
  }
}
