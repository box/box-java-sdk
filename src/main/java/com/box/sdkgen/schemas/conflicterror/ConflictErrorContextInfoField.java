package com.box.sdkgen.schemas.conflicterror;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.fileconflict.FileConflict;
import java.util.List;
import java.util.Objects;

public class ConflictErrorContextInfoField extends SerializableObject {

  protected List<FileConflict> conflicts;

  public ConflictErrorContextInfoField() {
    super();
  }

  protected ConflictErrorContextInfoField(ConflictErrorContextInfoFieldBuilder builder) {
    super();
    this.conflicts = builder.conflicts;
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

  public static class ConflictErrorContextInfoFieldBuilder {

    protected List<FileConflict> conflicts;

    public ConflictErrorContextInfoFieldBuilder conflicts(List<FileConflict> conflicts) {
      this.conflicts = conflicts;
      return this;
    }

    public ConflictErrorContextInfoField build() {
      return new ConflictErrorContextInfoField(this);
    }
  }
}
