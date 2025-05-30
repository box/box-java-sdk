package com.box.sdkgen.schemas.usercollaborations;

import com.box.sdkgen.schemas.userbase.UserBase;
import com.box.sdkgen.schemas.userbase.UserBaseTypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class UserCollaborations extends UserBase {

  protected String name;

  protected String login;

  @JsonProperty("is_active")
  protected Boolean isActive;

  public UserCollaborations(@JsonProperty("id") String id) {
    super(id);
  }

  protected UserCollaborations(UserCollaborationsBuilder builder) {
    super(builder);
    this.name = builder.name;
    this.login = builder.login;
    this.isActive = builder.isActive;
  }

  public String getName() {
    return name;
  }

  public String getLogin() {
    return login;
  }

  public Boolean getIsActive() {
    return isActive;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserCollaborations casted = (UserCollaborations) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name)
        && Objects.equals(login, casted.login)
        && Objects.equals(isActive, casted.isActive);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name, login, isActive);
  }

  @Override
  public String toString() {
    return "UserCollaborations{"
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
        + ", "
        + "isActive='"
        + isActive
        + '\''
        + "}";
  }

  public static class UserCollaborationsBuilder extends UserBaseBuilder {

    protected String name;

    protected String login;

    protected Boolean isActive;

    public UserCollaborationsBuilder(String id) {
      super(id);
    }

    public UserCollaborationsBuilder name(String name) {
      this.name = name;
      return this;
    }

    public UserCollaborationsBuilder login(String login) {
      this.login = login;
      return this;
    }

    public UserCollaborationsBuilder isActive(Boolean isActive) {
      this.isActive = isActive;
      return this;
    }

    @Override
    public UserCollaborationsBuilder type(UserBaseTypeField type) {
      this.type = new EnumWrapper<UserBaseTypeField>(type);
      return this;
    }

    @Override
    public UserCollaborationsBuilder type(EnumWrapper<UserBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public UserCollaborations build() {
      return new UserCollaborations(this);
    }
  }
}
