package com.box.sdkgen.managers.folders;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class CopyFolderRequestBody extends SerializableObject {

  protected String name;

  protected final CopyFolderRequestBodyParentField parent;

  public CopyFolderRequestBody(@JsonProperty("parent") CopyFolderRequestBodyParentField parent) {
    super();
    this.parent = parent;
  }

  protected CopyFolderRequestBody(CopyFolderRequestBodyBuilder builder) {
    super();
    this.name = builder.name;
    this.parent = builder.parent;
  }

  public String getName() {
    return name;
  }

  public CopyFolderRequestBodyParentField getParent() {
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
    CopyFolderRequestBody casted = (CopyFolderRequestBody) o;
    return Objects.equals(name, casted.name) && Objects.equals(parent, casted.parent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, parent);
  }

  @Override
  public String toString() {
    return "CopyFolderRequestBody{"
        + "name='"
        + name
        + '\''
        + ", "
        + "parent='"
        + parent
        + '\''
        + "}";
  }

  public static class CopyFolderRequestBodyBuilder {

    protected String name;

    protected final CopyFolderRequestBodyParentField parent;

    public CopyFolderRequestBodyBuilder(CopyFolderRequestBodyParentField parent) {
      this.parent = parent;
    }

    public CopyFolderRequestBodyBuilder name(String name) {
      this.name = name;
      return this;
    }

    public CopyFolderRequestBody build() {
      return new CopyFolderRequestBody(this);
    }
  }
}
