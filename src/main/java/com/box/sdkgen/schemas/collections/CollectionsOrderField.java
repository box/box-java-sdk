package com.box.sdkgen.schemas.collections;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class CollectionsOrderField extends SerializableObject {

  protected String by;

  @JsonDeserialize(
      using = CollectionsOrderDirectionField.CollectionsOrderDirectionFieldDeserializer.class)
  @JsonSerialize(
      using = CollectionsOrderDirectionField.CollectionsOrderDirectionFieldSerializer.class)
  protected EnumWrapper<CollectionsOrderDirectionField> direction;

  public CollectionsOrderField() {
    super();
  }

  protected CollectionsOrderField(CollectionsOrderFieldBuilder builder) {
    super();
    this.by = builder.by;
    this.direction = builder.direction;
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

  public static class CollectionsOrderFieldBuilder {

    protected String by;

    protected EnumWrapper<CollectionsOrderDirectionField> direction;

    public CollectionsOrderFieldBuilder by(String by) {
      this.by = by;
      return this;
    }

    public CollectionsOrderFieldBuilder direction(CollectionsOrderDirectionField direction) {
      this.direction = new EnumWrapper<CollectionsOrderDirectionField>(direction);
      return this;
    }

    public CollectionsOrderFieldBuilder direction(
        EnumWrapper<CollectionsOrderDirectionField> direction) {
      this.direction = direction;
      return this;
    }

    public CollectionsOrderField build() {
      return new CollectionsOrderField(this);
    }
  }
}
