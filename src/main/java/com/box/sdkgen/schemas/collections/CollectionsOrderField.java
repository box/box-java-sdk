package com.box.sdkgen.schemas.collections;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CollectionsOrderField extends SerializableObject {

  /** The field to order by. */
  protected String by;

  /** The direction to order by, either ascending or descending. */
  @JsonDeserialize(
      using = CollectionsOrderDirectionField.CollectionsOrderDirectionFieldDeserializer.class)
  @JsonSerialize(
      using = CollectionsOrderDirectionField.CollectionsOrderDirectionFieldSerializer.class)
  protected EnumWrapper<CollectionsOrderDirectionField> direction;

  public CollectionsOrderField() {
    super();
  }

  protected CollectionsOrderField(Builder builder) {
    super();
    this.by = builder.by;
    this.direction = builder.direction;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getBy() {
    return by;
  }

  public EnumWrapper<CollectionsOrderDirectionField> getDirection() {
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
    CollectionsOrderField casted = (CollectionsOrderField) o;
    return Objects.equals(by, casted.by) && Objects.equals(direction, casted.direction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(by, direction);
  }

  @Override
  public String toString() {
    return "CollectionsOrderField{"
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

    protected EnumWrapper<CollectionsOrderDirectionField> direction;

    public Builder by(String by) {
      this.by = by;
      return this;
    }

    public Builder direction(CollectionsOrderDirectionField direction) {
      this.direction = new EnumWrapper<CollectionsOrderDirectionField>(direction);
      return this;
    }

    public Builder direction(EnumWrapper<CollectionsOrderDirectionField> direction) {
      this.direction = direction;
      return this;
    }

    public CollectionsOrderField build() {
      return new CollectionsOrderField(this);
    }
  }
}
