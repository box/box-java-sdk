package com.box.sdkgen.schemas.userintegrationmappings;

import com.box.sdkgen.schemas.userbase.UserBase;
import com.box.sdkgen.schemas.userbase.UserBaseTypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class UserIntegrationMappings extends UserBase {

  protected String name;

  protected String login;

  public UserIntegrationMappings(@JsonProperty("id") String id) {
    super(id);
  }

  protected UserIntegrationMappings(UserIntegrationMappingsBuilder builder) {
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
    UserIntegrationMappings casted = (UserIntegrationMappings) o;
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
    return "UserIntegrationMappings{"
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

  public static class UserIntegrationMappingsBuilder extends UserBaseBuilder {

    protected String name;

    protected String login;

    public UserIntegrationMappingsBuilder(String id) {
      super(id);
    }

    public UserIntegrationMappingsBuilder name(String name) {
      this.name = name;
      return this;
    }

    public UserIntegrationMappingsBuilder login(String login) {
      this.login = login;
      return this;
    }

    @Override
    public UserIntegrationMappingsBuilder type(UserBaseTypeField type) {
      this.type = new EnumWrapper<UserBaseTypeField>(type);
      return this;
    }

    @Override
    public UserIntegrationMappingsBuilder type(EnumWrapper<UserBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public UserIntegrationMappings build() {
      return new UserIntegrationMappings(this);
    }
  }
}
