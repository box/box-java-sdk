package com.box.sdkgen.managers.uploads;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class PreflightFileUploadCheckRequestBody extends SerializableObject {

  protected String name;

  protected Integer size;

  protected PreflightFileUploadCheckRequestBodyParentField parent;

  public PreflightFileUploadCheckRequestBody() {
    super();
  }

  protected PreflightFileUploadCheckRequestBody(
      PreflightFileUploadCheckRequestBodyBuilder builder) {
    super();
    this.name = builder.name;
    this.size = builder.size;
    this.parent = builder.parent;
  }

  public String getName() {
    return name;
  }

  public Integer getSize() {
    return size;
  }

  public PreflightFileUploadCheckRequestBodyParentField getParent() {
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
    PreflightFileUploadCheckRequestBody casted = (PreflightFileUploadCheckRequestBody) o;
    return Objects.equals(name, casted.name)
        && Objects.equals(size, casted.size)
        && Objects.equals(parent, casted.parent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, size, parent);
  }

  @Override
  public String toString() {
    return "PreflightFileUploadCheckRequestBody{"
        + "name='"
        + name
        + '\''
        + ", "
        + "size='"
        + size
        + '\''
        + ", "
        + "parent='"
        + parent
        + '\''
        + "}";
  }

  public static class PreflightFileUploadCheckRequestBodyBuilder {

    protected String name;

    protected Integer size;

    protected PreflightFileUploadCheckRequestBodyParentField parent;

    public PreflightFileUploadCheckRequestBodyBuilder name(String name) {
      this.name = name;
      return this;
    }

    public PreflightFileUploadCheckRequestBodyBuilder size(Integer size) {
      this.size = size;
      return this;
    }

    public PreflightFileUploadCheckRequestBodyBuilder parent(
        PreflightFileUploadCheckRequestBodyParentField parent) {
      this.parent = parent;
      return this;
    }

    public PreflightFileUploadCheckRequestBody build() {
      return new PreflightFileUploadCheckRequestBody(this);
    }
  }
}
