package com.box.sdkgen.managers.taskassignments;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class CreateTaskAssignmentRequestBodyTaskField extends SerializableObject {

  protected final String id;

  @JsonDeserialize(
      using =
          CreateTaskAssignmentRequestBodyTaskTypeField
              .CreateTaskAssignmentRequestBodyTaskTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          CreateTaskAssignmentRequestBodyTaskTypeField
              .CreateTaskAssignmentRequestBodyTaskTypeFieldSerializer.class)
  protected EnumWrapper<CreateTaskAssignmentRequestBodyTaskTypeField> type;

  public CreateTaskAssignmentRequestBodyTaskField(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<CreateTaskAssignmentRequestBodyTaskTypeField>(
            CreateTaskAssignmentRequestBodyTaskTypeField.TASK);
  }

  protected CreateTaskAssignmentRequestBodyTaskField(
      CreateTaskAssignmentRequestBodyTaskFieldBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<CreateTaskAssignmentRequestBodyTaskTypeField> getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateTaskAssignmentRequestBodyTaskField casted = (CreateTaskAssignmentRequestBodyTaskField) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "CreateTaskAssignmentRequestBodyTaskField{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + "}";
  }

  public static class CreateTaskAssignmentRequestBodyTaskFieldBuilder {

    protected final String id;

    protected EnumWrapper<CreateTaskAssignmentRequestBodyTaskTypeField> type;

    public CreateTaskAssignmentRequestBodyTaskFieldBuilder(String id) {
      this.id = id;
      this.type =
          new EnumWrapper<CreateTaskAssignmentRequestBodyTaskTypeField>(
              CreateTaskAssignmentRequestBodyTaskTypeField.TASK);
    }

    public CreateTaskAssignmentRequestBodyTaskFieldBuilder type(
        CreateTaskAssignmentRequestBodyTaskTypeField type) {
      this.type = new EnumWrapper<CreateTaskAssignmentRequestBodyTaskTypeField>(type);
      return this;
    }

    public CreateTaskAssignmentRequestBodyTaskFieldBuilder type(
        EnumWrapper<CreateTaskAssignmentRequestBodyTaskTypeField> type) {
      this.type = type;
      return this;
    }

    public CreateTaskAssignmentRequestBodyTaskField build() {
      return new CreateTaskAssignmentRequestBodyTaskField(this);
    }
  }
}
