package com.box.sdkgen.schemas.usercollaborations;

import com.box.sdkgen.schemas.userbase.UserBase;
import com.box.sdkgen.schemas.userbase.UserBaseTypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** A mini representation of a user, can be returned only when the status is `pending`. */
@JsonFilter("nullablePropertyFilter")
public class UserCollaborations extends UserBase {

  /**
   * The display name of this user. If the collaboration status is `pending`, an empty string is
   * returned.
   */
  protected String name;

  /**
   * The primary email address of this user. If the collaboration status is `pending`, an empty
   * string is returned.
   */
  protected String login;

  /** If set to `false`, the user is either deactivated or deleted. */
  @JsonProperty("is_active")
  protected Boolean isActive;

  public UserCollaborations(@JsonProperty("id") String id) {
    super(id);
  }

  protected UserCollaborations(Builder builder) {
    super(builder);
    this.name = builder.name;
    this.login = builder.login;
    this.isActive = builder.isActive;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends UserBase.Builder {

    protected String name;

    protected String login;

    protected Boolean isActive;

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

    public Builder isActive(Boolean isActive) {
      this.isActive = isActive;
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

    public UserCollaborations build() {
      if (this.type == null) {
        this.type = new EnumWrapper<UserBaseTypeField>(UserBaseTypeField.USER);
      }
      return new UserCollaborations(this);
    }
  }
}
