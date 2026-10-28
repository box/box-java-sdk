package com.box.sdkgen.schemas.v2026r0.documentfailuresv2026r0;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/** Errors and warnings that occurred during a document job. */
@JsonFilter("nullablePropertyFilter")
public class DocumentFailuresV2026R0 extends SerializableObject {

  /** A list of errors that occurred during the document job. */
  protected final List<String> errors;

  /** A list of warnings that occurred during the document job. */
  protected final List<String> warnings;

  public DocumentFailuresV2026R0(
      @JsonProperty("errors") List<String> errors,
      @JsonProperty("warnings") List<String> warnings) {
    super();
    this.errors = errors;
    this.warnings = warnings;
  }

  public List<String> getErrors() {
    return errors;
  }

  public List<String> getWarnings() {
    return warnings;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DocumentFailuresV2026R0 casted = (DocumentFailuresV2026R0) o;
    return Objects.equals(errors, casted.errors) && Objects.equals(warnings, casted.warnings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errors, warnings);
  }

  @Override
  public String toString() {
    return "DocumentFailuresV2026R0{"
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
