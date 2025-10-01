package com.box.sdkgen.managers.invites;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateInviteRequestBodyActionableByField extends SerializableObject {

  /** The login of the invited user. */
  protected String login;

  public CreateInviteRequestBodyActionableByField() {
    super();
  }

  protected CreateInviteRequestBodyActionableByField(Builder builder) {
    super();
    this.login = builder.login;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends NullableFieldTracker {

    protected String login;

    public Builder login(String login) {
      this.login = login;
      return this;
    }

    public CreateInviteRequestBodyActionableByField build() {
      return new CreateInviteRequestBodyActionableByField(this);
    }
  }
}
