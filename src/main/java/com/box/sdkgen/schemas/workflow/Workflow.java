package com.box.sdkgen.schemas.workflow;

import com.box.sdkgen.schemas.workflowmini.WorkflowMini;
import com.box.sdkgen.schemas.workflowmini.WorkflowMiniTypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.List;
import java.util.Objects;

/**
 * Box Relay Workflows are objects that represent a named collection of flows.
 *
 * <p>Your application must be authorized to use the `Manage Box Relay` application scope within the
 * developer console in order to use this resource.
 */
@JsonFilter("nullablePropertyFilter")
public class Workflow extends WorkflowMini {

  /** A list of flows assigned to a workflow. */
  protected List<WorkflowFlowsField> flows;

  public Workflow() {
    super();
  }

  protected Workflow(Builder builder) {
    super(builder);
    this.flows = builder.flows;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<WorkflowFlowsField> getFlows() {
    return flows;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Workflow casted = (Workflow) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name)
        && Objects.equals(description, casted.description)
        && Objects.equals(isEnabled, casted.isEnabled)
        && Objects.equals(flows, casted.flows);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name, description, isEnabled, flows);
  }

  @Override
  public String toString() {
    return "Workflow{"
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
        + "description='"
        + description
        + '\''
        + ", "
        + "isEnabled='"
        + isEnabled
        + '\''
        + ", "
        + "flows='"
        + flows
        + '\''
        + "}";
  }

  public static class Builder extends WorkflowMini.Builder {

    protected List<WorkflowFlowsField> flows;

    public Builder flows(List<WorkflowFlowsField> flows) {
      this.flows = flows;
      return this;
    }

    @Override
    public Builder id(String id) {
      this.id = id;
      return this;
    }

    @Override
    public Builder type(WorkflowMiniTypeField type) {
      this.type = new EnumWrapper<WorkflowMiniTypeField>(type);
      return this;
    }

    @Override
    public Builder type(EnumWrapper<WorkflowMiniTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public Builder name(String name) {
      this.name = name;
      return this;
    }

    @Override
    public Builder description(String description) {
      this.description = description;
      return this;
    }

    @Override
    public Builder isEnabled(Boolean isEnabled) {
      this.isEnabled = isEnabled;
      return this;
    }

    public Workflow build() {
      return new Workflow(this);
    }
  }
}
