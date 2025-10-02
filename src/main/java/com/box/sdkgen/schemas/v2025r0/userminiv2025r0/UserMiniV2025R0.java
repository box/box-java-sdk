package com.box.sdkgen.schemas.v2025r0.userminiv2025r0;

import com.box.sdkgen.schemas.v2025r0.userbasev2025r0.UserBaseV2025R0;
import com.box.sdkgen.schemas.v2025r0.userbasev2025r0.UserBaseV2025R0TypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** A mini representation of a user, as can be returned when nested within other resources. */
@JsonFilter("nullablePropertyFilter")
public class UserMiniV2025R0 extends UserBaseV2025R0 {

  /** The display name of this user. */
  protected String name;

  /** The primary email address of this user. */
  protected String login;

  public UserMiniV2025R0(@JsonProperty("id") String id) {
    super(id);
  }

  protected UserMiniV2025R0(Builder builder) {
    super(builder);
    this.name = builder.name;
    this.login = builder.login;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getName() {
    return name;
  }

  public String getLogin() {
    return login;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserMiniV2025R0 casted = (UserMiniV2025R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name)
        && Objects.equals(login, casted.login);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name, login);
  }

  @Override
  public String toString() {
    return "UserMiniV2025R0{"
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
        + ", "
        + "login='"
        + login
        + '\''
        + "}";
  }

  public static class Builder extends UserBaseV2025R0.Builder {

    protected String name;

    protected String login;

    public Builder(String id) {
      super(id);
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder login(String login) {
      this.login = login;
      return this;
    }

    @Override
    public Builder type(UserBaseV2025R0TypeField type) {
      this.type = new EnumWrapper<UserBaseV2025R0TypeField>(type);
      return this;
    }

    @Override
    public Builder type(EnumWrapper<UserBaseV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public UserMiniV2025R0 build() {
      return new UserMiniV2025R0(this);
    }
  }
}
