package com.box.sdkgen.schemas.workflow;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class WorkflowFlowsOutcomesField extends SerializableObject {

  /** The identifier of the outcome. */
  protected String id;

  /** The outcomes resource type. */
  @JsonDeserialize(
      using = WorkflowFlowsOutcomesTypeField.WorkflowFlowsOutcomesTypeFieldDeserializer.class)
  @JsonSerialize(
      using = WorkflowFlowsOutcomesTypeField.WorkflowFlowsOutcomesTypeFieldSerializer.class)
  protected EnumWrapper<WorkflowFlowsOutcomesTypeField> type;

  /** The name of the outcome. */
  protected String name;

  @JsonDeserialize(
      using =
          WorkflowFlowsOutcomesActionTypeField.WorkflowFlowsOutcomesActionTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          WorkflowFlowsOutcomesActionTypeField.WorkflowFlowsOutcomesActionTypeFieldSerializer.class)
  @JsonProperty("action_type")
  protected EnumWrapper<WorkflowFlowsOutcomesActionTypeField> actionType;

  /**
   * If `action_type` is `assign_task` and the task is rejected, returns a list of outcomes to
   * complete.
   */
  @JsonProperty("if_rejected")
  protected List<WorkflowFlowsOutcomesIfRejectedField> ifRejected;

  public WorkflowFlowsOutcomesField() {
    super();
  }

  protected WorkflowFlowsOutcomesField(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.name = builder.name;
    this.actionType = builder.actionType;
    this.ifRejected = builder.ifRejected;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<WorkflowFlowsOutcomesTypeField> getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public EnumWrapper<WorkflowFlowsOutcomesActionTypeField> getActionType() {
    return actionType;
  }

  public List<WorkflowFlowsOutcomesIfRejectedField> getIfRejected() {
    return ifRejected;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WorkflowFlowsOutcomesField casted = (WorkflowFlowsOutcomesField) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name)
        && Objects.equals(actionType, casted.actionType)
        && Objects.equals(ifRejected, casted.ifRejected);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name, actionType, ifRejected);
  }

  @Override
  public String toString() {
    return "WorkflowFlowsOutcomesField{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + ", "
        + "actionType='"
        + actionType
        + '\''
        + ", "
        + "ifRejected='"
        + ifRejected
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<WorkflowFlowsOutcomesTypeField> type;

    protected String name;

    protected EnumWrapper<WorkflowFlowsOutcomesActionTypeField> actionType;

    protected List<WorkflowFlowsOutcomesIfRejectedField> ifRejected;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(WorkflowFlowsOutcomesTypeField type) {
      this.type = new EnumWrapper<WorkflowFlowsOutcomesTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<WorkflowFlowsOutcomesTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder actionType(WorkflowFlowsOutcomesActionTypeField actionType) {
      this.actionType = new EnumWrapper<WorkflowFlowsOutcomesActionTypeField>(actionType);
      return this;
    }

    public Builder actionType(EnumWrapper<WorkflowFlowsOutcomesActionTypeField> actionType) {
      this.actionType = actionType;
      return this;
    }

    public Builder ifRejected(List<WorkflowFlowsOutcomesIfRejectedField> ifRejected) {
      this.ifRejected = ifRejected;
      return this;
    }

    public WorkflowFlowsOutcomesField build() {
      return new WorkflowFlowsOutcomesField(this);
    }
  }
}
