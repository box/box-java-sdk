package com.box.sdkgen.schemas.uploadedpart;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.uploadpart.UploadPart;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

/** A chunk of a file uploaded as part of an upload session, as returned by some endpoints. */
@JsonFilter("nullablePropertyFilter")
public class UploadedPart extends SerializableObject {

  protected UploadPart part;

  public UploadedPart() {
    super();
  }

  protected UploadedPart(Builder builder) {
    super();
    this.part = builder.part;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends NullableFieldTracker {

    protected UploadPart part;

    public Builder part(UploadPart part) {
      this.part = part;
      return this;
    }

    public UploadedPart build() {
      return new UploadedPart(this);
    }
  }
}
