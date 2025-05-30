package com.box.sdkgen.schemas.task;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.filemini.FileMini;
import com.box.sdkgen.schemas.taskassignments.TaskAssignments;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class Task extends SerializableObject {

  protected String id;

  @JsonDeserialize(using = TaskTypeField.TaskTypeFieldDeserializer.class)
  @JsonSerialize(using = TaskTypeField.TaskTypeFieldSerializer.class)
  protected EnumWrapper<TaskTypeField> type;

  protected FileMini item;

  @JsonProperty("due_at")
  protected String dueAt;

  @JsonDeserialize(using = TaskActionField.TaskActionFieldDeserializer.class)
  @JsonSerialize(using = TaskActionField.TaskActionFieldSerializer.class)
  protected EnumWrapper<TaskActionField> action;

  protected String message;

  @JsonProperty("task_assignment_collection")
  protected TaskAssignments taskAssignmentCollection;

  @JsonProperty("is_completed")
  protected Boolean isCompleted;

  @JsonProperty("created_by")
  protected UserMini createdBy;

  @JsonProperty("created_at")
  protected String createdAt;

  @JsonDeserialize(using = TaskCompletionRuleField.TaskCompletionRuleFieldDeserializer.class)
  @JsonSerialize(using = TaskCompletionRuleField.TaskCompletionRuleFieldSerializer.class)
  @JsonProperty("completion_rule")
  protected EnumWrapper<TaskCompletionRuleField> completionRule;

  public Task() {
    super();
  }

  protected Task(TaskBuilder builder) {
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

  public String getDueAt() {
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

  public String getCreatedAt() {
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

  public static class TaskBuilder {

    protected String id;

    protected EnumWrapper<TaskTypeField> type;

    protected FileMini item;

    protected String dueAt;

    protected EnumWrapper<TaskActionField> action;

    protected String message;

    protected TaskAssignments taskAssignmentCollection;

    protected Boolean isCompleted;

    protected UserMini createdBy;

    protected String createdAt;

    protected EnumWrapper<TaskCompletionRuleField> completionRule;

    public TaskBuilder id(String id) {
      this.id = id;
      return this;
    }

    public TaskBuilder type(TaskTypeField type) {
      this.type = new EnumWrapper<TaskTypeField>(type);
      return this;
    }

    public TaskBuilder type(EnumWrapper<TaskTypeField> type) {
      this.type = type;
      return this;
    }

    public TaskBuilder item(FileMini item) {
      this.item = item;
      return this;
    }

    public TaskBuilder dueAt(String dueAt) {
      this.dueAt = dueAt;
      return this;
    }

    public TaskBuilder action(TaskActionField action) {
      this.action = new EnumWrapper<TaskActionField>(action);
      return this;
    }

    public TaskBuilder action(EnumWrapper<TaskActionField> action) {
      this.action = action;
      return this;
    }

    public TaskBuilder message(String message) {
      this.message = message;
      return this;
    }

    public TaskBuilder taskAssignmentCollection(TaskAssignments taskAssignmentCollection) {
      this.taskAssignmentCollection = taskAssignmentCollection;
      return this;
    }

    public TaskBuilder isCompleted(Boolean isCompleted) {
      this.isCompleted = isCompleted;
      return this;
    }

    public TaskBuilder createdBy(UserMini createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public TaskBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public TaskBuilder completionRule(TaskCompletionRuleField completionRule) {
      this.completionRule = new EnumWrapper<TaskCompletionRuleField>(completionRule);
      return this;
    }

    public TaskBuilder completionRule(EnumWrapper<TaskCompletionRuleField> completionRule) {
      this.completionRule = completionRule;
      return this;
    }

    public Task build() {
      return new Task(this);
    }
  }
}
