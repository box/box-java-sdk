package com.box.sdkgen.managers.files;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CopyFileRequestBody extends SerializableObject {

  protected String name;

  protected String version;

  protected final CopyFileRequestBodyParentField parent;

  public CopyFileRequestBody(@JsonProperty("parent") CopyFileRequestBodyParentField parent) {
    super();
    this.parent = parent;
  }

  protected CopyFileRequestBody(Builder builder) {
    super();
    this.name = builder.name;
    this.version = builder.version;
    this.parent = builder.parent;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getName() {
    return name;
  }

  public String getVersion() {
    return version;
  }

  public CopyFileRequestBodyParentField getParent() {
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
    CopyFileRequestBody casted = (CopyFileRequestBody) o;
    return Objects.equals(name, casted.name)
        && Objects.equals(version, casted.version)
        && Objects.equals(parent, casted.parent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, version, parent);
  }

  @Override
  public String toString() {
    return "CopyFileRequestBody{"
        + "name='"
        + name
        + '\''
        + ", "
        + "version='"
        + version
        + '\''
        + ", "
        + "parent='"
        + parent
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String name;

    protected String version;

    protected final CopyFileRequestBodyParentField parent;

    public Builder(CopyFileRequestBodyParentField parent) {
      super();
      this.parent = parent;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder version(String version) {
      this.version = version;
      return this;
    }

    public CopyFileRequestBody build() {
      return new CopyFileRequestBody(this);
    }
  }
}
