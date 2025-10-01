package com.box.sdkgen.schemas.conflicterror;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.fileconflict.FileConflict;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ConflictErrorContextInfoField extends SerializableObject {

  /** A list of the file conflicts that caused this error. */
  protected List<FileConflict> conflicts;

  public ConflictErrorContextInfoField() {
    super();
  }

  protected ConflictErrorContextInfoField(Builder builder) {
    super();
    this.conflicts = builder.conflicts;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<FileConflict> getConflicts() {
    return conflicts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConflictErrorContextInfoField casted = (ConflictErrorContextInfoField) o;
    return Objects.equals(conflicts, casted.conflicts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(conflicts);
  }

  @Override
  public String toString() {
    return "ConflictErrorContextInfoField{" + "conflicts='" + conflicts + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected List<FileConflict> conflicts;

    public Builder conflicts(List<FileConflict> conflicts) {
      this.conflicts = conflicts;
      return this;
    }

    public ConflictErrorContextInfoField build() {
      return new ConflictErrorContextInfoField(this);
    }
  }
}
