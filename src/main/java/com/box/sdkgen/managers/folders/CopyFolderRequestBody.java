package com.box.sdkgen.managers.folders;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CopyFolderRequestBody extends SerializableObject {

  /**
   * An optional new name for the copied folder.
   *
   * <p>There are some restrictions to the file name. Names containing non-printable ASCII
   * characters, forward and backward slashes (`/`, `\`), as well as names with trailing spaces are
   * prohibited.
   *
   * <p>Additionally, the names `.` and `..` are not allowed either.
   */
  protected String name;

  /** The destination folder to copy the folder to. */
  protected final CopyFolderRequestBodyParentField parent;

  public CopyFolderRequestBody(@JsonProperty("parent") CopyFolderRequestBodyParentField parent) {
    super();
    this.parent = parent;
  }

  protected CopyFolderRequestBody(Builder builder) {
    super();
    this.name = builder.name;
    this.parent = builder.parent;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends NullableFieldTracker {

    protected String name;

    protected final CopyFolderRequestBodyParentField parent;

    public Builder(CopyFolderRequestBodyParentField parent) {
      super();
      this.parent = parent;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public CopyFolderRequestBody build() {
      return new CopyFolderRequestBody(this);
    }
  }
}
