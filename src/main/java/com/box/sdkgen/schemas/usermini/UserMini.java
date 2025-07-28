package com.box.sdkgen.schemas.usermini;

import com.box.sdkgen.schemas.userbase.UserBase;
import com.box.sdkgen.schemas.userbase.UserBaseTypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UserMini extends UserBase {

  protected String name;

  protected String login;

  public UserMini(@JsonProperty("id") String id) {
    super(id);
  }

  protected UserMini(Builder builder) {
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
    UserMini casted = (UserMini) o;
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
    return "UserMini{"
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

  public static class Builder extends UserBase.Builder {

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
    public Builder type(UserBaseTypeField type) {
      this.type = new EnumWrapper<UserBaseTypeField>(type);
      return this;
    }

    @Override
    public Builder type(EnumWrapper<UserBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public UserMini build() {
      return new UserMini(this);
    }
  }
}
