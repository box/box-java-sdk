package com.box.sdkgen.schemas.users;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class UsersOrderField extends SerializableObject {

  protected String by;

  @JsonDeserialize(using = UsersOrderDirectionField.UsersOrderDirectionFieldDeserializer.class)
  @JsonSerialize(using = UsersOrderDirectionField.UsersOrderDirectionFieldSerializer.class)
  protected EnumWrapper<UsersOrderDirectionField> direction;

  public UsersOrderField() {
    super();
  }

  protected UsersOrderField(UsersOrderFieldBuilder builder) {
    super();
    this.by = builder.by;
    this.direction = builder.direction;
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

  public static class UsersOrderFieldBuilder {

    protected String by;

    protected EnumWrapper<UsersOrderDirectionField> direction;

    public UsersOrderFieldBuilder by(String by) {
      this.by = by;
      return this;
    }

    public UsersOrderFieldBuilder direction(UsersOrderDirectionField direction) {
      this.direction = new EnumWrapper<UsersOrderDirectionField>(direction);
      return this;
    }

    public UsersOrderFieldBuilder direction(EnumWrapper<UsersOrderDirectionField> direction) {
      this.direction = direction;
      return this;
    }

    public UsersOrderField build() {
      return new UsersOrderField(this);
    }
  }
}
