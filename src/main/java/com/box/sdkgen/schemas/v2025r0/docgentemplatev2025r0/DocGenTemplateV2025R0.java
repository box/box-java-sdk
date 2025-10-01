package com.box.sdkgen.schemas.v2025r0.docgentemplatev2025r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.schemas.v2025r0.docgentemplatebasev2025r0.DocGenTemplateBaseV2025R0;
import com.box.sdkgen.schemas.v2025r0.filereferencev2025r0.FileReferenceV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** A Box Doc Gen template object. */
@JsonFilter("nullablePropertyFilter")
public class DocGenTemplateV2025R0 extends DocGenTemplateBaseV2025R0 {

  /** The name of the template. */
  @JsonProperty("file_name")
  @Nullable
  protected String fileName;

  public DocGenTemplateV2025R0() {
    super();
  }

  protected DocGenTemplateV2025R0(Builder builder) {
    super(builder);
    this.fileName = builder.fileName;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getFileName() {
    return fileName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocGenTemplateV2025R0 casted = (DocGenTemplateV2025R0) o;
    return Objects.equals(file, casted.file) && Objects.equals(fileName, casted.fileName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(file, fileName);
  }

  @Override
  public String toString() {
    return "DocGenTemplateV2025R0{"
        + "file='"
        + file
        + '\''
        + ", "
        + "fileName='"
        + fileName
        + '\''
        + "}";
  }

  public static class Builder extends DocGenTemplateBaseV2025R0.Builder {

    protected String fileName;

    public Builder fileName(String fileName) {
      this.fileName = fileName;
      this.markNullableFieldAsSet("file_name");
      return this;
    }

    @Override
    public Builder file(FileReferenceV2025R0 file) {
      this.file = file;
      return this;
    }

    public DocGenTemplateV2025R0 build() {
      return new DocGenTemplateV2025R0(this);
    }
  }
}
