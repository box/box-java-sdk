package com.box.sdkgen.schemas.items;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class ItemsOrderField extends SerializableObject {

  protected String by;

  @JsonDeserialize(using = ItemsOrderDirectionField.ItemsOrderDirectionFieldDeserializer.class)
  @JsonSerialize(using = ItemsOrderDirectionField.ItemsOrderDirectionFieldSerializer.class)
  protected EnumWrapper<ItemsOrderDirectionField> direction;

  public ItemsOrderField() {
    super();
  }

  protected ItemsOrderField(ItemsOrderFieldBuilder builder) {
    super();
    this.by = builder.by;
    this.direction = builder.direction;
  }

  public String getBy() {
    return by;
  }

  public EnumWrapper<ItemsOrderDirectionField> getDirection() {
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
    ItemsOrderField casted = (ItemsOrderField) o;
    return Objects.equals(by, casted.by) && Objects.equals(direction, casted.direction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(by, direction);
  }

  @Override
  public String toString() {
    return "ItemsOrderField{" + "by='" + by + '\'' + ", " + "direction='" + direction + '\'' + "}";
  }

  public static class ItemsOrderFieldBuilder {

    protected String by;

    protected EnumWrapper<ItemsOrderDirectionField> direction;

    public ItemsOrderFieldBuilder by(String by) {
      this.by = by;
      return this;
    }

    public ItemsOrderFieldBuilder direction(ItemsOrderDirectionField direction) {
      this.direction = new EnumWrapper<ItemsOrderDirectionField>(direction);
      return this;
    }

    public ItemsOrderFieldBuilder direction(EnumWrapper<ItemsOrderDirectionField> direction) {
      this.direction = direction;
      return this;
    }

    public ItemsOrderField build() {
      return new ItemsOrderField(this);
    }
  }
}
