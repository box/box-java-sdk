package com.box.sdkgen.schemas.userbase;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** A mini representation of a user, used when nested within another resource. */
@JsonFilter("nullablePropertyFilter")
public class UserBase extends SerializableObject {

  /** The unique identifier for this user. */
  protected final String id;

  /** The value will always be `user`. */
  @JsonDeserialize(using = UserBaseTypeField.UserBaseTypeFieldDeserializer.class)
  @JsonSerialize(using = UserBaseTypeField.UserBaseTypeFieldSerializer.class)
  protected EnumWrapper<UserBaseTypeField> type;

  public UserBase(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type = new EnumWrapper<UserBaseTypeField>(UserBaseTypeField.USER);
  }

  protected UserBase(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<UserBaseTypeField> getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserBase casted = (UserBase) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "UserBase{" + "id='" + id + '\'' + ", " + "type='" + type + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<UserBaseTypeField> type;

    public Builder(String id) {
      super();
      this.id = id;
    }

    public Builder type(UserBaseTypeField type) {
      this.type = new EnumWrapper<UserBaseTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<UserBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public UserBase build() {
      if (this.type == null) {
        this.type = new EnumWrapper<UserBaseTypeField>(UserBaseTypeField.USER);
      }
      return new UserBase(this);
    }
  }
}
