package com.box.sdkgen.schemas.itemsoffsetpaginated;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ItemsOffsetPaginatedOrderField extends SerializableObject {

  /** The field to order by. */
  protected String by;

  /** The direction to order by, either ascending or descending. */
  @JsonDeserialize(
      using =
          ItemsOffsetPaginatedOrderDirectionField
              .ItemsOffsetPaginatedOrderDirectionFieldDeserializer.class)
  @JsonSerialize(
      using =
          ItemsOffsetPaginatedOrderDirectionField.ItemsOffsetPaginatedOrderDirectionFieldSerializer
              .class)
  protected EnumWrapper<ItemsOffsetPaginatedOrderDirectionField> direction;

  public ItemsOffsetPaginatedOrderField() {
    super();
  }

  protected ItemsOffsetPaginatedOrderField(Builder builder) {
    super();
    this.by = builder.by;
    this.direction = builder.direction;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getBy() {
    return by;
  }

  public EnumWrapper<ItemsOffsetPaginatedOrderDirectionField> getDirection() {
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
    ItemsOffsetPaginatedOrderField casted = (ItemsOffsetPaginatedOrderField) o;
    return Objects.equals(by, casted.by) && Objects.equals(direction, casted.direction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(by, direction);
  }

  @Override
  public String toString() {
    return "ItemsOffsetPaginatedOrderField{"
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

    protected EnumWrapper<ItemsOffsetPaginatedOrderDirectionField> direction;

    public Builder by(String by) {
      this.by = by;
      return this;
    }

    public Builder direction(ItemsOffsetPaginatedOrderDirectionField direction) {
      this.direction = new EnumWrapper<ItemsOffsetPaginatedOrderDirectionField>(direction);
      return this;
    }

    public Builder direction(EnumWrapper<ItemsOffsetPaginatedOrderDirectionField> direction) {
      this.direction = direction;
      return this;
    }

    public ItemsOffsetPaginatedOrderField build() {
      return new ItemsOffsetPaginatedOrderField(this);
    }
  }
}
