package com.box.sdkgen.schemas.uploadedpart;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.uploadpart.UploadPart;
import java.util.Objects;

public class UploadedPart extends SerializableObject {

  protected UploadPart part;

  public UploadedPart() {
    super();
  }

  protected UploadedPart(UploadedPartBuilder builder) {
    super();
    this.part = builder.part;
  }

  public UploadPart getPart() {
    return part;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UploadedPart casted = (UploadedPart) o;
    return Objects.equals(part, casted.part);
  }

  @Override
  public int hashCode() {
    return Objects.hash(part);
  }

  @Override
  public String toString() {
    return "UploadedPart{" + "part='" + part + '\'' + "}";
  }

  public static class UploadedPartBuilder {

    protected UploadPart part;

    public UploadedPartBuilder part(UploadPart part) {
      this.part = part;
      return this;
    }

    public UploadedPart build() {
      return new UploadedPart(this);
    }
  }
}
