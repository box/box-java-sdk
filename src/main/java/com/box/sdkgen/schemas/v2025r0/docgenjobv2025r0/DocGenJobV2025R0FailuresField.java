package com.box.sdkgen.schemas.v2025r0.docgenjobv2025r0;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class DocGenJobV2025R0FailuresField extends SerializableObject {

  /** A list of errors that occurred during document generation. */
  protected final List<String> errors;

  /** A list of warnings that occurred during document generation. */
  protected final List<String> warnings;

  public DocGenJobV2025R0FailuresField(
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
    DocGenJobV2025R0FailuresField casted = (DocGenJobV2025R0FailuresField) o;
    return Objects.equals(errors, casted.errors) && Objects.equals(warnings, casted.warnings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errors, warnings);
  }

  @Override
  public String toString() {
    return "DocGenJobV2025R0FailuresField{"
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
