package com.box.sdkgen.managers.invites;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class CreateInviteRequestBodyActionableByField extends SerializableObject {

  protected String login;

  public CreateInviteRequestBodyActionableByField() {
    super();
  }

  protected CreateInviteRequestBodyActionableByField(
      CreateInviteRequestBodyActionableByFieldBuilder builder) {
    super();
    this.login = builder.login;
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
    CreateInviteRequestBodyActionableByField casted = (CreateInviteRequestBodyActionableByField) o;
    return Objects.equals(login, casted.login);
  }

  @Override
  public int hashCode() {
    return Objects.hash(login);
  }

  @Override
  public String toString() {
    return "CreateInviteRequestBodyActionableByField{" + "login='" + login + '\'' + "}";
  }

  public static class CreateInviteRequestBodyActionableByFieldBuilder {

    protected String login;

    public CreateInviteRequestBodyActionableByFieldBuilder login(String login) {
      this.login = login;
      return this;
    }

    public CreateInviteRequestBodyActionableByField build() {
      return new CreateInviteRequestBodyActionableByField(this);
    }
  }
}
