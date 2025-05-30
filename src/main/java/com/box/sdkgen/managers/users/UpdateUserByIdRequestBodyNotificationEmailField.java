package com.box.sdkgen.managers.users;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class UpdateUserByIdRequestBodyNotificationEmailField extends SerializableObject {

  protected String email;

  public UpdateUserByIdRequestBodyNotificationEmailField() {
    super();
  }

  protected UpdateUserByIdRequestBodyNotificationEmailField(
      UpdateUserByIdRequestBodyNotificationEmailFieldBuilder builder) {
    super();
    this.email = builder.email;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateUserByIdRequestBodyNotificationEmailField casted =
        (UpdateUserByIdRequestBodyNotificationEmailField) o;
    return Objects.equals(email, casted.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email);
  }

  @Override
  public String toString() {
    return "UpdateUserByIdRequestBodyNotificationEmailField{" + "email='" + email + '\'' + "}";
  }

  public static class UpdateUserByIdRequestBodyNotificationEmailFieldBuilder {

    protected String email;

    public UpdateUserByIdRequestBodyNotificationEmailFieldBuilder email(String email) {
      this.email = email;
      return this;
    }

    public UpdateUserByIdRequestBodyNotificationEmailField build() {
      return new UpdateUserByIdRequestBodyNotificationEmailField(this);
    }
  }
}
