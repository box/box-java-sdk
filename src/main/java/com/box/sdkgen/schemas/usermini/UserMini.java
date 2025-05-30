package com.box.sdkgen.schemas.usermini;

import com.box.sdkgen.schemas.userbase.UserBase;
import com.box.sdkgen.schemas.userbase.UserBaseTypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class UserMini extends UserBase {

  protected String name;

  protected String login;

  public UserMini(@JsonProperty("id") String id) {
    super(id);
  }

  protected UserMini(UserMiniBuilder builder) {
    super(builder);
    this.name = builder.name;
    this.login = builder.login;
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

  public static class UserMiniBuilder extends UserBaseBuilder {

    protected String name;

    protected String login;

    public UserMiniBuilder(String id) {
      super(id);
    }

    public UserMiniBuilder name(String name) {
      this.name = name;
      return this;
    }

    public UserMiniBuilder login(String login) {
      this.login = login;
      return this;
    }

    @Override
    public UserMiniBuilder type(UserBaseTypeField type) {
      this.type = new EnumWrapper<UserBaseTypeField>(type);
      return this;
    }

    @Override
    public UserMiniBuilder type(EnumWrapper<UserBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public UserMini build() {
      return new UserMini(this);
    }
  }
}
