package com.box.sdkgen.schemas.taskassignment;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.filemini.FileMini;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
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
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date completedAt;

  @JsonProperty("assigned_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date assignedAt;

  @JsonProperty("reminded_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date remindedAt;

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

  protected TaskAssignment(Builder builder) {
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
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public Date getCompletedAt() {
    return completedAt;
  }

  public Date getAssignedAt() {
    return assignedAt;
  }

  public Date getRemindedAt() {
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

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<TaskAssignmentTypeField> type;

    protected FileMini item;

    protected UserMini assignedTo;

    protected String message;

    protected Date completedAt;

    protected Date assignedAt;

    protected Date remindedAt;

    protected EnumWrapper<TaskAssignmentResolutionStateField> resolutionState;

    protected UserMini assignedBy;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(TaskAssignmentTypeField type) {
      this.type = new EnumWrapper<TaskAssignmentTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<TaskAssignmentTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder item(FileMini item) {
      this.item = item;
      return this;
    }

    public Builder assignedTo(UserMini assignedTo) {
      this.assignedTo = assignedTo;
      return this;
    }

    public Builder message(String message) {
      this.message = message;
      return this;
    }

    public Builder completedAt(Date completedAt) {
      this.completedAt = completedAt;
      return this;
    }

    public Builder assignedAt(Date assignedAt) {
      this.assignedAt = assignedAt;
      return this;
    }

    public Builder remindedAt(Date remindedAt) {
      this.remindedAt = remindedAt;
      return this;
    }

    public Builder resolutionState(TaskAssignmentResolutionStateField resolutionState) {
      this.resolutionState = new EnumWrapper<TaskAssignmentResolutionStateField>(resolutionState);
      return this;
    }

    public Builder resolutionState(
        EnumWrapper<TaskAssignmentResolutionStateField> resolutionState) {
      this.resolutionState = resolutionState;
      return this;
    }

    public Builder assignedBy(UserMini assignedBy) {
      this.assignedBy = assignedBy;
      return this;
    }

    public TaskAssignment build() {
      return new TaskAssignment(this);
    }
  }
}
