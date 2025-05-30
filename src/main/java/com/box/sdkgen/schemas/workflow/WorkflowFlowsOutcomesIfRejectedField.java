package com.box.sdkgen.schemas.workflow;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

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

  protected WorkflowFlowsOutcomesIfRejectedField(
      WorkflowFlowsOutcomesIfRejectedFieldBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.name = builder.name;
    this.actionType = builder.actionType;
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

  public static class WorkflowFlowsOutcomesIfRejectedFieldBuilder {

    protected String id;

    protected EnumWrapper<WorkflowFlowsOutcomesIfRejectedTypeField> type;

    protected String name;

    protected EnumWrapper<WorkflowFlowsOutcomesIfRejectedActionTypeField> actionType;

    public WorkflowFlowsOutcomesIfRejectedFieldBuilder id(String id) {
      this.id = id;
      return this;
    }

    public WorkflowFlowsOutcomesIfRejectedFieldBuilder type(
        WorkflowFlowsOutcomesIfRejectedTypeField type) {
      this.type = new EnumWrapper<WorkflowFlowsOutcomesIfRejectedTypeField>(type);
      return this;
    }

    public WorkflowFlowsOutcomesIfRejectedFieldBuilder type(
        EnumWrapper<WorkflowFlowsOutcomesIfRejectedTypeField> type) {
      this.type = type;
      return this;
    }

    public WorkflowFlowsOutcomesIfRejectedFieldBuilder name(String name) {
      this.name = name;
      return this;
    }

    public WorkflowFlowsOutcomesIfRejectedFieldBuilder actionType(
        WorkflowFlowsOutcomesIfRejectedActionTypeField actionType) {
      this.actionType = new EnumWrapper<WorkflowFlowsOutcomesIfRejectedActionTypeField>(actionType);
      return this;
    }

    public WorkflowFlowsOutcomesIfRejectedFieldBuilder actionType(
        EnumWrapper<WorkflowFlowsOutcomesIfRejectedActionTypeField> actionType) {
      this.actionType = actionType;
      return this;
    }

    public WorkflowFlowsOutcomesIfRejectedField build() {
      return new WorkflowFlowsOutcomesIfRejectedField(this);
    }
  }
}
