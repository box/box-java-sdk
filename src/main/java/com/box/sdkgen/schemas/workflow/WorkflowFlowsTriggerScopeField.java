package com.box.sdkgen.schemas.workflow;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class WorkflowFlowsTriggerScopeField extends SerializableObject {

  /** The trigger scope's resource type. */
  @JsonDeserialize(
      using =
          WorkflowFlowsTriggerScopeTypeField.WorkflowFlowsTriggerScopeTypeFieldDeserializer.class)
  @JsonSerialize(
      using = WorkflowFlowsTriggerScopeTypeField.WorkflowFlowsTriggerScopeTypeFieldSerializer.class)
  protected EnumWrapper<WorkflowFlowsTriggerScopeTypeField> type;

  /** Indicates the path of the condition value to check. */
  protected String ref;

  /** The object the `ref` points to. */
  protected WorkflowFlowsTriggerScopeObjectField object;

  public WorkflowFlowsTriggerScopeField() {
    super();
  }

  protected WorkflowFlowsTriggerScopeField(Builder builder) {
    super();
    this.type = builder.type;
    this.ref = builder.ref;
    this.object = builder.object;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<WorkflowFlowsTriggerScopeTypeField> getType() {
    return type;
  }

  public String getRef() {
    return ref;
  }

  public WorkflowFlowsTriggerScopeObjectField getObject() {
    return object;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WorkflowFlowsTriggerScopeField casted = (WorkflowFlowsTriggerScopeField) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(ref, casted.ref)
        && Objects.equals(object, casted.object);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, ref, object);
  }

  @Override
  public String toString() {
    return "WorkflowFlowsTriggerScopeField{"
        + "type='"
        + type
        + '\''
        + ", "
        + "ref='"
        + ref
        + '\''
        + ", "
        + "object='"
        + object
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<WorkflowFlowsTriggerScopeTypeField> type;

    protected String ref;

    protected WorkflowFlowsTriggerScopeObjectField object;

    public Builder type(WorkflowFlowsTriggerScopeTypeField type) {
      this.type = new EnumWrapper<WorkflowFlowsTriggerScopeTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<WorkflowFlowsTriggerScopeTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder ref(String ref) {
      this.ref = ref;
      return this;
    }

    public Builder object(WorkflowFlowsTriggerScopeObjectField object) {
      this.object = object;
      return this;
    }

    public WorkflowFlowsTriggerScopeField build() {
      return new WorkflowFlowsTriggerScopeField(this);
    }
  }
}
