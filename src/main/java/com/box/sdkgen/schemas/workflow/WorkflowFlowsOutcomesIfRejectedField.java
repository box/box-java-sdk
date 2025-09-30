package com.box.sdkgen.schemas.workflow;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class WorkflowFlowsOutcomesIfRejectedField extends SerializableObject {

  protected String id;

  @JsonDeserialize(
      using =
          WorkflowFlowsOutcomesIfRejectedTypeField
              .WorkflowFlowsOutcomesIfRejectedTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          WorkflowFlowsOutcomesIfRejectedTypeField
              .WorkflowFlowsOutcomesIfRejectedTypeFieldSerializer.class)
  protected EnumWrapper<WorkflowFlowsOutcomesIfRejectedTypeField> type;

  protected String name;

  @JsonDeserialize(
      using =
          WorkflowFlowsOutcomesIfRejectedActionTypeField
              .WorkflowFlowsOutcomesIfRejectedActionTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          WorkflowFlowsOutcomesIfRejectedActionTypeField
              .WorkflowFlowsOutcomesIfRejectedActionTypeFieldSerializer.class)
  @JsonProperty("action_type")
  protected EnumWrapper<WorkflowFlowsOutcomesIfRejectedActionTypeField> actionType;

  public WorkflowFlowsOutcomesIfRejectedField() {
    super();
  }

  protected WorkflowFlowsOutcomesIfRejectedField(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.name = builder.name;
    this.actionType = builder.actionType;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<WorkflowFlowsOutcomesIfRejectedTypeField> getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public EnumWrapper<WorkflowFlowsOutcomesIfRejectedActionTypeField> getActionType() {
    return actionType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WorkflowFlowsOutcomesIfRejectedField casted = (WorkflowFlowsOutcomesIfRejectedField) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name)
        && Objects.equals(actionType, casted.actionType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name, actionType);
  }

  @Override
  public String toString() {
    return "WorkflowFlowsOutcomesIfRejectedField{"
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
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<WorkflowFlowsOutcomesIfRejectedTypeField> type;

    protected String name;

    protected EnumWrapper<WorkflowFlowsOutcomesIfRejectedActionTypeField> actionType;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(WorkflowFlowsOutcomesIfRejectedTypeField type) {
      this.type = new EnumWrapper<WorkflowFlowsOutcomesIfRejectedTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<WorkflowFlowsOutcomesIfRejectedTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder actionType(WorkflowFlowsOutcomesIfRejectedActionTypeField actionType) {
      this.actionType = new EnumWrapper<WorkflowFlowsOutcomesIfRejectedActionTypeField>(actionType);
      return this;
    }

    public Builder actionType(
        EnumWrapper<WorkflowFlowsOutcomesIfRejectedActionTypeField> actionType) {
      this.actionType = actionType;
      return this;
    }

    public WorkflowFlowsOutcomesIfRejectedField build() {
      return new WorkflowFlowsOutcomesIfRejectedField(this);
    }
  }
}
