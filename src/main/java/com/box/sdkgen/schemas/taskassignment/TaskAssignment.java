package com.box.sdkgen.schemas.taskassignment;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.filemini.FileMini;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class TaskAssignment extends SerializableObject {

  protected String id;

  @JsonDeserialize(using = TaskAssignmentTypeField.TaskAssignmentTypeFieldDeserializer.class)
  @JsonSerialize(using = TaskAssignmentTypeField.TaskAssignmentTypeFieldSerializer.class)
  protected EnumWrapper<TaskAssignmentTypeField> type;

  protected FileMini item;

  @JsonProperty("assigned_to")
  protected UserMini assignedTo;

  protected String message;

  @JsonProperty("completed_at")
  protected String completedAt;

  @JsonProperty("assigned_at")
  protected String assignedAt;

  @JsonProperty("reminded_at")
  protected String remindedAt;

  @JsonDeserialize(
      using =
          TaskAssignmentResolutionStateField.TaskAssignmentResolutionStateFieldDeserializer.class)
  @JsonSerialize(
      using = TaskAssignmentResolutionStateField.TaskAssignmentResolutionStateFieldSerializer.class)
  @JsonProperty("resolution_state")
  protected EnumWrapper<TaskAssignmentResolutionStateField> resolutionState;

  @JsonProperty("assigned_by")
  protected UserMini assignedBy;

  public TaskAssignment() {
    super();
  }

  protected TaskAssignment(TaskAssignmentBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.item = builder.item;
    this.assignedTo = builder.assignedTo;
    this.message = builder.message;
    this.completedAt = builder.completedAt;
    this.assignedAt = builder.assignedAt;
    this.remindedAt = builder.remindedAt;
    this.resolutionState = builder.resolutionState;
    this.assignedBy = builder.assignedBy;
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<TaskAssignmentTypeField> getType() {
    return type;
  }

  public FileMini getItem() {
    return item;
  }

  public UserMini getAssignedTo() {
    return assignedTo;
  }

  public String getMessage() {
    return message;
  }

  public String getCompletedAt() {
    return completedAt;
  }

  public String getAssignedAt() {
    return assignedAt;
  }

  public String getRemindedAt() {
    return remindedAt;
  }

  public EnumWrapper<TaskAssignmentResolutionStateField> getResolutionState() {
    return resolutionState;
  }

  public UserMini getAssignedBy() {
    return assignedBy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TaskAssignment casted = (TaskAssignment) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(item, casted.item)
        && Objects.equals(assignedTo, casted.assignedTo)
        && Objects.equals(message, casted.message)
        && Objects.equals(completedAt, casted.completedAt)
        && Objects.equals(assignedAt, casted.assignedAt)
        && Objects.equals(remindedAt, casted.remindedAt)
        && Objects.equals(resolutionState, casted.resolutionState)
        && Objects.equals(assignedBy, casted.assignedBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        type,
        item,
        assignedTo,
        message,
        completedAt,
        assignedAt,
        remindedAt,
        resolutionState,
        assignedBy);
  }

  @Override
  public String toString() {
    return "TaskAssignment{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "item='"
        + item
        + '\''
        + ", "
        + "assignedTo='"
        + assignedTo
        + '\''
        + ", "
        + "message='"
        + message
        + '\''
        + ", "
        + "completedAt='"
        + completedAt
        + '\''
        + ", "
        + "assignedAt='"
        + assignedAt
        + '\''
        + ", "
        + "remindedAt='"
        + remindedAt
        + '\''
        + ", "
        + "resolutionState='"
        + resolutionState
        + '\''
        + ", "
        + "assignedBy='"
        + assignedBy
        + '\''
        + "}";
  }

  public static class TaskAssignmentBuilder {

    protected String id;

    protected EnumWrapper<TaskAssignmentTypeField> type;

    protected FileMini item;

    protected UserMini assignedTo;

    protected String message;

    protected String completedAt;

    protected String assignedAt;

    protected String remindedAt;

    protected EnumWrapper<TaskAssignmentResolutionStateField> resolutionState;

    protected UserMini assignedBy;

    public TaskAssignmentBuilder id(String id) {
      this.id = id;
      return this;
    }

    public TaskAssignmentBuilder type(TaskAssignmentTypeField type) {
      this.type = new EnumWrapper<TaskAssignmentTypeField>(type);
      return this;
    }

    public TaskAssignmentBuilder type(EnumWrapper<TaskAssignmentTypeField> type) {
      this.type = type;
      return this;
    }

    public TaskAssignmentBuilder item(FileMini item) {
      this.item = item;
      return this;
    }

    public TaskAssignmentBuilder assignedTo(UserMini assignedTo) {
      this.assignedTo = assignedTo;
      return this;
    }

    public TaskAssignmentBuilder message(String message) {
      this.message = message;
      return this;
    }

    public TaskAssignmentBuilder completedAt(String completedAt) {
      this.completedAt = completedAt;
      return this;
    }

    public TaskAssignmentBuilder assignedAt(String assignedAt) {
      this.assignedAt = assignedAt;
      return this;
    }

    public TaskAssignmentBuilder remindedAt(String remindedAt) {
      this.remindedAt = remindedAt;
      return this;
    }

    public TaskAssignmentBuilder resolutionState(
        TaskAssignmentResolutionStateField resolutionState) {
      this.resolutionState = new EnumWrapper<TaskAssignmentResolutionStateField>(resolutionState);
      return this;
    }

    public TaskAssignmentBuilder resolutionState(
        EnumWrapper<TaskAssignmentResolutionStateField> resolutionState) {
      this.resolutionState = resolutionState;
      return this;
    }

    public TaskAssignmentBuilder assignedBy(UserMini assignedBy) {
      this.assignedBy = assignedBy;
      return this;
    }

    public TaskAssignment build() {
      return new TaskAssignment(this);
    }
  }
}
