package com.box.sdkgen.schemas.itemsoffsetpaginated;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class ItemsOffsetPaginatedOrderField extends SerializableObject {

  protected String by;

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

  protected ItemsOffsetPaginatedOrderField(ItemsOffsetPaginatedOrderFieldBuilder builder) {
    super();
    this.by = builder.by;
    this.direction = builder.direction;
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

  public static class ItemsOffsetPaginatedOrderFieldBuilder {

    protected String by;

    protected EnumWrapper<ItemsOffsetPaginatedOrderDirectionField> direction;

    public ItemsOffsetPaginatedOrderFieldBuilder by(String by) {
      this.by = by;
      return this;
    }

    public ItemsOffsetPaginatedOrderFieldBuilder direction(
        ItemsOffsetPaginatedOrderDirectionField direction) {
      this.direction = new EnumWrapper<ItemsOffsetPaginatedOrderDirectionField>(direction);
      return this;
    }

    public ItemsOffsetPaginatedOrderFieldBuilder direction(
        EnumWrapper<ItemsOffsetPaginatedOrderDirectionField> direction) {
      this.direction = direction;
      return this;
    }

    public ItemsOffsetPaginatedOrderField build() {
      return new ItemsOffsetPaginatedOrderField(this);
    }
  }
}
