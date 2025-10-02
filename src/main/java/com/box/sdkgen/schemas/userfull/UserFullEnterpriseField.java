package com.box.sdkgen.schemas.userfull;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UserFullEnterpriseField extends SerializableObject {

  /** The unique identifier for this enterprise. */
  protected String id;

  /** The value will always be `enterprise`. */
  @JsonDeserialize(
      using = UserFullEnterpriseTypeField.UserFullEnterpriseTypeFieldDeserializer.class)
  @JsonSerialize(using = UserFullEnterpriseTypeField.UserFullEnterpriseTypeFieldSerializer.class)
  protected EnumWrapper<UserFullEnterpriseTypeField> type;

  /** The name of the enterprise. */
  protected String name;

  public UserFullEnterpriseField() {
    super();
  }

  protected UserFullEnterpriseField(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.name = builder.name;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<UserFullEnterpriseTypeField> getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserFullEnterpriseField casted = (UserFullEnterpriseField) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name);
  }

  @Override
  public String toString() {
    return "UserFullEnterpriseField{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<UserFullEnterpriseTypeField> type;

    protected String name;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(UserFullEnterpriseTypeField type) {
      this.type = new EnumWrapper<UserFullEnterpriseTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<UserFullEnterpriseTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public UserFullEnterpriseField build() {
      return new UserFullEnterpriseField(this);
    }
  }
}
