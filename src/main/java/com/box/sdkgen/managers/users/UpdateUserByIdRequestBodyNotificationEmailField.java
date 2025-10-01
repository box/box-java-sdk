package com.box.sdkgen.managers.users;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateUserByIdRequestBodyNotificationEmailField extends SerializableObject {

  /** The email address to send the notifications to. */
  protected String email;

  public UpdateUserByIdRequestBodyNotificationEmailField() {
    super();
  }

  protected UpdateUserByIdRequestBodyNotificationEmailField(Builder builder) {
    super();
    this.email = builder.email;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends NullableFieldTracker {

    protected String email;

    public Builder email(String email) {
      this.email = email;
      return this;
    }

    public UpdateUserByIdRequestBodyNotificationEmailField build() {
      return new UpdateUserByIdRequestBodyNotificationEmailField(this);
    }
  }
}
