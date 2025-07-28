package com.box.sdkgen.schemas.user;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UserNotificationEmailField extends SerializableObject {

  protected String email;

  @JsonProperty("is_confirmed")
  protected Boolean isConfirmed;

  public UserNotificationEmailField() {
    super();
  }

  protected UserNotificationEmailField(Builder builder) {
    super();
    this.email = builder.email;
    this.isConfirmed = builder.isConfirmed;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getEmail() {
    return email;
  }

  public Boolean getIsConfirmed() {
    return isConfirmed;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserNotificationEmailField casted = (UserNotificationEmailField) o;
    return Objects.equals(email, casted.email) && Objects.equals(isConfirmed, casted.isConfirmed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, isConfirmed);
  }

  @Override
  public String toString() {
    return "UserNotificationEmailField{"
        + "email='"
        + email
        + '\''
        + ", "
        + "isConfirmed='"
        + isConfirmed
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String email;

    protected Boolean isConfirmed;

    public Builder email(String email) {
      this.email = email;
      return this;
    }

    public Builder isConfirmed(Boolean isConfirmed) {
      this.isConfirmed = isConfirmed;
      return this;
    }

    public UserNotificationEmailField build() {
      return new UserNotificationEmailField(this);
    }
  }
}
