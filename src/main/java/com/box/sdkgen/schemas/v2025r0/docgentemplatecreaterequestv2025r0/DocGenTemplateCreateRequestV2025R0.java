package com.box.sdkgen.schemas.v2025r0.docgentemplatecreaterequestv2025r0;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2025r0.filereferencev2025r0.FileReferenceV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class DocGenTemplateCreateRequestV2025R0 extends SerializableObject {

  protected final FileReferenceV2025R0 file;

  public DocGenTemplateCreateRequestV2025R0(@JsonProperty("file") FileReferenceV2025R0 file) {
    super();
    this.file = file;
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
    DocGenTemplateCreateRequestV2025R0 casted = (DocGenTemplateCreateRequestV2025R0) o;
    return Objects.equals(file, casted.file);
  }

  @Override
  public int hashCode() {
    return Objects.hash(file);
  }

  @Override
  public String toString() {
    return "DocGenTemplateCreateRequestV2025R0{" + "file='" + file + '\'' + "}";
  }
}
