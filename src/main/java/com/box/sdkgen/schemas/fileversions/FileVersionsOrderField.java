package com.box.sdkgen.schemas.fileversions;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FileVersionsOrderField extends SerializableObject {

  /** The field to order by. */
  protected String by;

  /** The direction to order by, either ascending or descending. */
  @JsonDeserialize(
      using = FileVersionsOrderDirectionField.FileVersionsOrderDirectionFieldDeserializer.class)
  @JsonSerialize(
      using = FileVersionsOrderDirectionField.FileVersionsOrderDirectionFieldSerializer.class)
  protected EnumWrapper<FileVersionsOrderDirectionField> direction;

  public FileVersionsOrderField() {
    super();
  }

  protected FileVersionsOrderField(Builder builder) {
    super();
    this.by = builder.by;
    this.direction = builder.direction;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getBy() {
    return by;
  }

  public EnumWrapper<FileVersionsOrderDirectionField> getDirection() {
    return direction;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileVersionsOrderField casted = (FileVersionsOrderField) o;
    return Objects.equals(by, casted.by) && Objects.equals(direction, casted.direction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(by, direction);
  }

  @Override
  public String toString() {
    return "FileVersionsOrderField{"
        + "by='"
        + by
        + '\''
        + ", "
        + "direction='"
        + direction
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String by;

    protected EnumWrapper<FileVersionsOrderDirectionField> direction;

    public Builder by(String by) {
      this.by = by;
      return this;
    }

    public Builder direction(FileVersionsOrderDirectionField direction) {
      this.direction = new EnumWrapper<FileVersionsOrderDirectionField>(direction);
      return this;
    }

    public Builder direction(EnumWrapper<FileVersionsOrderDirectionField> direction) {
      this.direction = direction;
      return this;
    }

    public FileVersionsOrderField build() {
      return new FileVersionsOrderField(this);
    }
  }
}
