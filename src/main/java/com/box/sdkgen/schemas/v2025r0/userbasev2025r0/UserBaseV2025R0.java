package com.box.sdkgen.schemas.v2025r0.userbasev2025r0;

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
public class UserBaseV2025R0 extends SerializableObject {

  /** The unique identifier for this user. */
  protected final String id;

  /** The value will always be `user`. */
  @JsonDeserialize(using = UserBaseV2025R0TypeField.UserBaseV2025R0TypeFieldDeserializer.class)
  @JsonSerialize(using = UserBaseV2025R0TypeField.UserBaseV2025R0TypeFieldSerializer.class)
  protected EnumWrapper<UserBaseV2025R0TypeField> type;

  public UserBaseV2025R0(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type = new EnumWrapper<UserBaseV2025R0TypeField>(UserBaseV2025R0TypeField.USER);
  }

  protected UserBaseV2025R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<UserBaseV2025R0TypeField> getType() {
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
    UserBaseV2025R0 casted = (UserBaseV2025R0) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "UserBaseV2025R0{" + "id='" + id + '\'' + ", " + "type='" + type + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<UserBaseV2025R0TypeField> type;

    public Builder(String id) {
      super();
      this.id = id;
    }

    public Builder type(UserBaseV2025R0TypeField type) {
      this.type = new EnumWrapper<UserBaseV2025R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<UserBaseV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public UserBaseV2025R0 build() {
      if (this.type == null) {
        this.type = new EnumWrapper<UserBaseV2025R0TypeField>(UserBaseV2025R0TypeField.USER);
      }
      return new UserBaseV2025R0(this);
    }
  }
}
