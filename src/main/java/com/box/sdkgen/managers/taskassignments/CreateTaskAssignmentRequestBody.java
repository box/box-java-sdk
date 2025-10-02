package com.box.sdkgen.managers.taskassignments;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateTaskAssignmentRequestBody extends SerializableObject {

  /** The task to assign to a user. */
  protected final CreateTaskAssignmentRequestBodyTaskField task;

  /** The user to assign the task to. */
  @JsonProperty("assign_to")
  protected final CreateTaskAssignmentRequestBodyAssignToField assignTo;

  public CreateTaskAssignmentRequestBody(
      @JsonProperty("task") CreateTaskAssignmentRequestBodyTaskField task,
      @JsonProperty("assign_to") CreateTaskAssignmentRequestBodyAssignToField assignTo) {
    super();
    this.task = task;
    this.assignTo = assignTo;
  }

  public CreateTaskAssignmentRequestBodyTaskField getTask() {
    return task;
  }

  public CreateTaskAssignmentRequestBodyAssignToField getAssignTo() {
    return assignTo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateTaskAssignmentRequestBody casted = (CreateTaskAssignmentRequestBody) o;
    return Objects.equals(task, casted.task) && Objects.equals(assignTo, casted.assignTo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(task, assignTo);
  }

  @Override
  public String toString() {
    return "CreateTaskAssignmentRequestBody{"
        + "task='"
        + task
        + '\''
        + ", "
        + "assignTo='"
        + assignTo
        + '\''
        + "}";
  }
}
