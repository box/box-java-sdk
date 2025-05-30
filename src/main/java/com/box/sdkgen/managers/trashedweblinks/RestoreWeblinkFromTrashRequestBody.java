package com.box.sdkgen.managers.trashedweblinks;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class RestoreWeblinkFromTrashRequestBody extends SerializableObject {

  protected String name;

  protected RestoreWeblinkFromTrashRequestBodyParentField parent;

  public RestoreWeblinkFromTrashRequestBody() {
    super();
  }

  protected RestoreWeblinkFromTrashRequestBody(RestoreWeblinkFromTrashRequestBodyBuilder builder) {
    super();
    this.name = builder.name;
    this.parent = builder.parent;
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

  public static class RestoreWeblinkFromTrashRequestBodyBuilder {

    protected String name;

    protected RestoreWeblinkFromTrashRequestBodyParentField parent;

    public RestoreWeblinkFromTrashRequestBodyBuilder name(String name) {
      this.name = name;
      return this;
    }

    public RestoreWeblinkFromTrashRequestBodyBuilder parent(
        RestoreWeblinkFromTrashRequestBodyParentField parent) {
      this.parent = parent;
      return this;
    }

    public RestoreWeblinkFromTrashRequestBody build() {
      return new RestoreWeblinkFromTrashRequestBody(this);
    }
  }
}
