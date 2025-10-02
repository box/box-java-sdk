package com.box.sdkgen.schemas.devicepinners;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class DevicePinnersOrderField extends SerializableObject {

  /** The field that is ordered by. */
  @JsonDeserialize(using = DevicePinnersOrderByField.DevicePinnersOrderByFieldDeserializer.class)
  @JsonSerialize(using = DevicePinnersOrderByField.DevicePinnersOrderByFieldSerializer.class)
  protected EnumWrapper<DevicePinnersOrderByField> by;

  /** The direction to order by, either ascending or descending. */
  @JsonDeserialize(
      using = DevicePinnersOrderDirectionField.DevicePinnersOrderDirectionFieldDeserializer.class)
  @JsonSerialize(
      using = DevicePinnersOrderDirectionField.DevicePinnersOrderDirectionFieldSerializer.class)
  protected EnumWrapper<DevicePinnersOrderDirectionField> direction;

  public DevicePinnersOrderField() {
    super();
  }

  protected DevicePinnersOrderField(Builder builder) {
    super();
    this.by = builder.by;
    this.direction = builder.direction;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<DevicePinnersOrderByField> getBy() {
    return by;
  }

  public EnumWrapper<DevicePinnersOrderDirectionField> getDirection() {
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
    DevicePinnersOrderField casted = (DevicePinnersOrderField) o;
    return Objects.equals(by, casted.by) && Objects.equals(direction, casted.direction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(by, direction);
  }

  @Override
  public String toString() {
    return "DevicePinnersOrderField{"
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

    protected EnumWrapper<DevicePinnersOrderByField> by;

    protected EnumWrapper<DevicePinnersOrderDirectionField> direction;

    public Builder by(DevicePinnersOrderByField by) {
      this.by = new EnumWrapper<DevicePinnersOrderByField>(by);
      return this;
    }

    public Builder by(EnumWrapper<DevicePinnersOrderByField> by) {
      this.by = by;
      return this;
    }

    public Builder direction(DevicePinnersOrderDirectionField direction) {
      this.direction = new EnumWrapper<DevicePinnersOrderDirectionField>(direction);
      return this;
    }

    public Builder direction(EnumWrapper<DevicePinnersOrderDirectionField> direction) {
      this.direction = direction;
      return this;
    }

    public DevicePinnersOrderField build() {
      return new DevicePinnersOrderField(this);
    }
  }
}
