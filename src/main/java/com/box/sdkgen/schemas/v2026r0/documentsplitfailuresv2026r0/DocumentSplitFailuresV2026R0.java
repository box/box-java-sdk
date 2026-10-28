package com.box.sdkgen.schemas.v2026r0.documentsplitfailuresv2026r0;

import com.box.sdkgen.schemas.v2026r0.documentfailuresv2026r0.DocumentFailuresV2026R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** Errors and warnings that occurred during a Document Split job. */
@JsonFilter("nullablePropertyFilter")
public class DocumentSplitFailuresV2026R0 extends DocumentFailuresV2026R0 {

  public DocumentSplitFailuresV2026R0(
      @JsonProperty("errors") List<String> errors,
      @JsonProperty("warnings") List<String> warnings) {
    super(errors, warnings);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocumentSplitFailuresV2026R0 casted = (DocumentSplitFailuresV2026R0) o;
    return Objects.equals(errors, casted.errors) && Objects.equals(warnings, casted.warnings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errors, warnings);
  }

  @Override
  public String toString() {
    return "DocumentSplitFailuresV2026R0{"
        + "errors='"
        + errors
        + '\''
        + ", "
        + "warnings='"
        + warnings
        + '\''
        + "}";
  }
}
