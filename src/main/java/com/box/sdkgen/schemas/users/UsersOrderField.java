package com.box.sdkgen.schemas.users;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UsersOrderField extends SerializableObject {

  /** The field to order by. */
  protected String by;

  /** The direction to order by, either ascending or descending. */
  @JsonDeserialize(using = UsersOrderDirectionField.UsersOrderDirectionFieldDeserializer.class)
  @JsonSerialize(using = UsersOrderDirectionField.UsersOrderDirectionFieldSerializer.class)
  protected EnumWrapper<UsersOrderDirectionField> direction;

  public UsersOrderField() {
    super();
  }

  protected UsersOrderField(Builder builder) {
    super();
    this.by = builder.by;
    this.direction = builder.direction;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getBy() {
    return by;
  }

  public EnumWrapper<UsersOrderDirectionField> getDirection() {
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
    UsersOrderField casted = (UsersOrderField) o;
    return Objects.equals(by, casted.by) && Objects.equals(direction, casted.direction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(by, direction);
  }

  @Override
  public String toString() {
    return "UsersOrderField{" + "by='" + by + '\'' + ", " + "direction='" + direction + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String by;

    protected EnumWrapper<UsersOrderDirectionField> direction;

    public Builder by(String by) {
      this.by = by;
      return this;
    }

    public Builder direction(UsersOrderDirectionField direction) {
      this.direction = new EnumWrapper<UsersOrderDirectionField>(direction);
      return this;
    }

    public Builder direction(EnumWrapper<UsersOrderDirectionField> direction) {
      this.direction = direction;
      return this;
    }

    public UsersOrderField build() {
      return new UsersOrderField(this);
    }
  }
}
