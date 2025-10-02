package com.box.sdkgen.schemas.workflow;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class WorkflowFlowsTriggerScopeObjectField extends SerializableObject {

  /** The type of the object. */
  @JsonDeserialize(
      using =
          WorkflowFlowsTriggerScopeObjectTypeField
              .WorkflowFlowsTriggerScopeObjectTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          WorkflowFlowsTriggerScopeObjectTypeField
              .WorkflowFlowsTriggerScopeObjectTypeFieldSerializer.class)
  protected EnumWrapper<WorkflowFlowsTriggerScopeObjectTypeField> type;

  /** The id of the object. */
  protected String id;

  public WorkflowFlowsTriggerScopeObjectField() {
    super();
  }

  protected WorkflowFlowsTriggerScopeObjectField(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<WorkflowFlowsTriggerScopeObjectTypeField> getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WorkflowFlowsTriggerScopeObjectField casted = (WorkflowFlowsTriggerScopeObjectField) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "WorkflowFlowsTriggerScopeObjectField{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<WorkflowFlowsTriggerScopeObjectTypeField> type;

    protected String id;

    public Builder type(WorkflowFlowsTriggerScopeObjectTypeField type) {
      this.type = new EnumWrapper<WorkflowFlowsTriggerScopeObjectTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<WorkflowFlowsTriggerScopeObjectTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public WorkflowFlowsTriggerScopeObjectField build() {
      return new WorkflowFlowsTriggerScopeObjectField(this);
    }
  }
}
