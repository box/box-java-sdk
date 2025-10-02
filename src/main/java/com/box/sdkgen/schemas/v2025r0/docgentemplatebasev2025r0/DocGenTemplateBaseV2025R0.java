package com.box.sdkgen.schemas.v2025r0.docgentemplatebasev2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2025r0.filereferencev2025r0.FileReferenceV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

/** A base representation of a Box Doc Gen template, used when nested within another resource. */
@JsonFilter("nullablePropertyFilter")
public class DocGenTemplateBaseV2025R0 extends SerializableObject {

  protected FileReferenceV2025R0 file;

  public DocGenTemplateBaseV2025R0() {
    super();
  }

  protected DocGenTemplateBaseV2025R0(Builder builder) {
    super();
    this.file = builder.file;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public FileReferenceV2025R0 getFile() {
    return file;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocGenTemplateBaseV2025R0 casted = (DocGenTemplateBaseV2025R0) o;
    return Objects.equals(file, casted.file);
  }

  @Override
  public int hashCode() {
    return Objects.hash(file);
  }

  @Override
  public String toString() {
    return "DocGenTemplateBaseV2025R0{" + "file='" + file + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected FileReferenceV2025R0 file;

    public Builder file(FileReferenceV2025R0 file) {
      this.file = file;
      return this;
    }

    public DocGenTemplateBaseV2025R0 build() {
      return new DocGenTemplateBaseV2025R0(this);
    }
  }
}
