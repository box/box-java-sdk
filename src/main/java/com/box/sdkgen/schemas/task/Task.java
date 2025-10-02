package com.box.sdkgen.schemas.task;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.filemini.FileMini;
import com.box.sdkgen.schemas.taskassignments.TaskAssignments;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * A task allows for file-centric workflows within Box. Users can create tasks on files and assign
 * them to other users for them to complete the tasks.
 */
@JsonFilter("nullablePropertyFilter")
public class Task extends SerializableObject {

  /** The unique identifier for this task. */
  protected String id;

  /** The value will always be `task`. */
  @JsonDeserialize(using = TaskTypeField.TaskTypeFieldDeserializer.class)
  @JsonSerialize(using = TaskTypeField.TaskTypeFieldSerializer.class)
  protected EnumWrapper<TaskTypeField> type;

  protected FileMini item;

  /** When the task is due. */
  @JsonProperty("due_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime dueAt;

  /** The type of task the task assignee will be prompted to perform. */
  @JsonDeserialize(using = TaskActionField.TaskActionFieldDeserializer.class)
  @JsonSerialize(using = TaskActionField.TaskActionFieldSerializer.class)
  protected EnumWrapper<TaskActionField> action;

  /** A message that will be included with the task. */
  protected String message;

  @JsonProperty("task_assignment_collection")
  protected TaskAssignments taskAssignmentCollection;

  /** Whether the task has been completed. */
  @JsonProperty("is_completed")
  protected Boolean isCompleted;

  @JsonProperty("created_by")
  protected UserMini createdBy;

  /** When the task object was created. */
  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime createdAt;

  /**
   * Defines which assignees need to complete this task before the task is considered completed.
   *
   * <p>* `all_assignees` requires all assignees to review or approve the task in order for it to be
   * considered completed. * `any_assignee` accepts any one assignee to review or approve the task
   * in order for it to be considered completed.
   */
  @JsonDeserialize(using = TaskCompletionRuleField.TaskCompletionRuleFieldDeserializer.class)
  @JsonSerialize(using = TaskCompletionRuleField.TaskCompletionRuleFieldSerializer.class)
  @JsonProperty("completion_rule")
  protected EnumWrapper<TaskCompletionRuleField> completionRule;

  public Task() {
    super();
  }

  protected Task(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.item = builder.item;
    this.dueAt = builder.dueAt;
    this.action = builder.action;
    this.message = builder.message;
    this.taskAssignmentCollection = builder.taskAssignmentCollection;
    this.isCompleted = builder.isCompleted;
    this.createdBy = builder.createdBy;
    this.createdAt = builder.createdAt;
    this.completionRule = builder.completionRule;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<TaskTypeField> getType() {
    return type;
  }

  public FileMini getItem() {
    return item;
  }

  public OffsetDateTime getDueAt() {
    return dueAt;
  }

  public EnumWrapper<TaskActionField> getAction() {
    return action;
  }

  public String getMessage() {
    return message;
  }

  public TaskAssignments getTaskAssignmentCollection() {
    return taskAssignmentCollection;
  }

  public Boolean getIsCompleted() {
    return isCompleted;
  }

  public UserMini getCreatedBy() {
    return createdBy;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public EnumWrapper<TaskCompletionRuleField> getCompletionRule() {
    return completionRule;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Task casted = (Task) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(item, casted.item)
        && Objects.equals(dueAt, casted.dueAt)
        && Objects.equals(action, casted.action)
        && Objects.equals(message, casted.message)
        && Objects.equals(taskAssignmentCollection, casted.taskAssignmentCollection)
        && Objects.equals(isCompleted, casted.isCompleted)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(completionRule, casted.completionRule);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        type,
        item,
        dueAt,
        action,
        message,
        taskAssignmentCollection,
        isCompleted,
        createdBy,
        createdAt,
        completionRule);
  }

  @Override
  public String toString() {
    return "Task{"
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
        + "dueAt='"
        + dueAt
        + '\''
        + ", "
        + "action='"
        + action
        + '\''
        + ", "
        + "message='"
        + message
        + '\''
        + ", "
        + "taskAssignmentCollection='"
        + taskAssignmentCollection
        + '\''
        + ", "
        + "isCompleted='"
        + isCompleted
        + '\''
        + ", "
        + "createdBy='"
        + createdBy
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "completionRule='"
        + completionRule
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<TaskTypeField> type;

    protected FileMini item;

    protected OffsetDateTime dueAt;

    protected EnumWrapper<TaskActionField> action;

    protected String message;

    protected TaskAssignments taskAssignmentCollection;

    protected Boolean isCompleted;

    protected UserMini createdBy;

    protected OffsetDateTime createdAt;

    protected EnumWrapper<TaskCompletionRuleField> completionRule;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(TaskTypeField type) {
      this.type = new EnumWrapper<TaskTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<TaskTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder item(FileMini item) {
      this.item = item;
      return this;
    }

    public Builder dueAt(OffsetDateTime dueAt) {
      this.dueAt = dueAt;
      return this;
    }

    public Builder action(TaskActionField action) {
      this.action = new EnumWrapper<TaskActionField>(action);
      return this;
    }

    public Builder action(EnumWrapper<TaskActionField> action) {
      this.action = action;
      return this;
    }

    public Builder message(String message) {
      this.message = message;
      return this;
    }

    public Builder taskAssignmentCollection(TaskAssignments taskAssignmentCollection) {
      this.taskAssignmentCollection = taskAssignmentCollection;
      return this;
    }

    public Builder isCompleted(Boolean isCompleted) {
      this.isCompleted = isCompleted;
      return this;
    }

    public Builder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder completionRule(TaskCompletionRuleField completionRule) {
      this.completionRule = new EnumWrapper<TaskCompletionRuleField>(completionRule);
      return this;
    }

    public Builder completionRule(EnumWrapper<TaskCompletionRuleField> completionRule) {
      this.completionRule = completionRule;
      return this;
    }

    public Task build() {
      return new Task(this);
    }
  }
}
