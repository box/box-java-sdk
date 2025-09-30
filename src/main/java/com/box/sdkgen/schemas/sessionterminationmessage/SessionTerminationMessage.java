package com.box.sdkgen.schemas.sessionterminationmessage;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class SessionTerminationMessage extends SerializableObject {

  protected String message;

  public SessionTerminationMessage() {
    super();
  }

  protected SessionTerminationMessage(Builder builder) {
    super();
    this.message = builder.message;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getMessage() {
    return message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SessionTerminationMessage casted = (SessionTerminationMessage) o;
    return Objects.equals(message, casted.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message);
  }

  @Override
  public String toString() {
    return "SessionTerminationMessage{" + "message='" + message + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String message;

    public Builder message(String message) {
      this.message = message;
      return this;
    }

    public SessionTerminationMessage build() {
      return new SessionTerminationMessage(this);
    }
  }
}
