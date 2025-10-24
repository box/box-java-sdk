package com.box.sdkgen.schemas.v2025r0.userorgroupreferencev2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** A reference to a user or group. */
@JsonFilter("nullablePropertyFilter")
public class UserOrGroupReferenceV2025R0 extends SerializableObject {

  /** The type `user` or `group`. */
  @JsonDeserialize(
      using =
          UserOrGroupReferenceV2025R0TypeField.UserOrGroupReferenceV2025R0TypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          UserOrGroupReferenceV2025R0TypeField.UserOrGroupReferenceV2025R0TypeFieldSerializer.class)
  protected EnumWrapper<UserOrGroupReferenceV2025R0TypeField> type;

  /** The identifier of the user or group. */
  protected String id;

  public UserOrGroupReferenceV2025R0() {
    super();
  }

  protected UserOrGroupReferenceV2025R0(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<UserOrGroupReferenceV2025R0TypeField> getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserOrGroupReferenceV2025R0 casted = (UserOrGroupReferenceV2025R0) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "UserOrGroupReferenceV2025R0{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<UserOrGroupReferenceV2025R0TypeField> type;

    protected String id;

    public Builder type(UserOrGroupReferenceV2025R0TypeField type) {
      this.type = new EnumWrapper<UserOrGroupReferenceV2025R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<UserOrGroupReferenceV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public UserOrGroupReferenceV2025R0 build() {
      return new UserOrGroupReferenceV2025R0(this);
    }
  }
}
