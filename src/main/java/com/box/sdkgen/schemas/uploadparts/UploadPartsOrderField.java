package com.box.sdkgen.schemas.uploadparts;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UploadPartsOrderField extends SerializableObject {

  protected String by;

  @JsonDeserialize(
      using = UploadPartsOrderDirectionField.UploadPartsOrderDirectionFieldDeserializer.class)
  @JsonSerialize(
      using = UploadPartsOrderDirectionField.UploadPartsOrderDirectionFieldSerializer.class)
  protected EnumWrapper<UploadPartsOrderDirectionField> direction;

  public UploadPartsOrderField() {
    super();
  }

  protected UploadPartsOrderField(Builder builder) {
    super();
    this.by = builder.by;
    this.direction = builder.direction;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getBy() {
    return by;
  }

  public EnumWrapper<UploadPartsOrderDirectionField> getDirection() {
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
    UploadPartsOrderField casted = (UploadPartsOrderField) o;
    return Objects.equals(by, casted.by) && Objects.equals(direction, casted.direction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(by, direction);
  }

  @Override
  public String toString() {
    return "UploadPartsOrderField{"
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

    protected EnumWrapper<UploadPartsOrderDirectionField> direction;

    public Builder by(String by) {
      this.by = by;
      return this;
    }

    public Builder direction(UploadPartsOrderDirectionField direction) {
      this.direction = new EnumWrapper<UploadPartsOrderDirectionField>(direction);
      return this;
    }

    public Builder direction(EnumWrapper<UploadPartsOrderDirectionField> direction) {
      this.direction = direction;
      return this;
    }

    public UploadPartsOrderField build() {
      return new UploadPartsOrderField(this);
    }
  }
}
