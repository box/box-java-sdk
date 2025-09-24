package com.box.sdkgen.managers.taskassignments;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateTaskAssignmentRequestBodyAssignToField extends SerializableObject {

  protected String id;

  protected String login;

  public CreateTaskAssignmentRequestBodyAssignToField() {
    super();
  }

  protected CreateTaskAssignmentRequestBodyAssignToField(Builder builder) {
    super();
    this.id = builder.id;
    this.login = builder.login;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
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
    CreateTaskAssignmentRequestBodyAssignToField casted =
        (CreateTaskAssignmentRequestBodyAssignToField) o;
    return Objects.equals(id, casted.id) && Objects.equals(login, casted.login);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, login);
  }

  @Override
  public String toString() {
    return "CreateTaskAssignmentRequestBodyAssignToField{"
        + "id='"
        + id
        + '\''
        + ", "
        + "login='"
        + login
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected String login;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder login(String login) {
      this.login = login;
      return this;
    }

    public CreateTaskAssignmentRequestBodyAssignToField build() {
      return new CreateTaskAssignmentRequestBodyAssignToField(this);
    }
  }
}
