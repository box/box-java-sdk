package com.box.sdkgen.managers.trashedfolders;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class RestoreFolderFromTrashRequestBody extends SerializableObject {

  protected String name;

  protected RestoreFolderFromTrashRequestBodyParentField parent;

  public RestoreFolderFromTrashRequestBody() {
    super();
  }

  protected RestoreFolderFromTrashRequestBody(RestoreFolderFromTrashRequestBodyBuilder builder) {
    super();
    this.name = builder.name;
    this.parent = builder.parent;
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

  public static class RestoreFolderFromTrashRequestBodyBuilder {

    protected String name;

    protected RestoreFolderFromTrashRequestBodyParentField parent;

    public RestoreFolderFromTrashRequestBodyBuilder name(String name) {
      this.name = name;
      return this;
    }

    public RestoreFolderFromTrashRequestBodyBuilder parent(
        RestoreFolderFromTrashRequestBodyParentField parent) {
      this.parent = parent;
      return this;
    }

    public RestoreFolderFromTrashRequestBody build() {
      return new RestoreFolderFromTrashRequestBody(this);
    }
  }
}
