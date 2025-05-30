package com.box.sdkgen.schemas.workflow;

import com.box.sdkgen.schemas.workflowmini.WorkflowMini;
import com.box.sdkgen.schemas.workflowmini.WorkflowMiniTypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import java.util.List;
import java.util.Objects;

public class Workflow extends WorkflowMini {

  protected List<WorkflowFlowsField> flows;

  public Workflow() {
    super();
  }

  protected Workflow(WorkflowBuilder builder) {
    super(builder);
    this.flows = builder.flows;
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

  public static class WorkflowBuilder extends WorkflowMiniBuilder {

    protected List<WorkflowFlowsField> flows;

    public WorkflowBuilder flows(List<WorkflowFlowsField> flows) {
      this.flows = flows;
      return this;
    }

    @Override
    public WorkflowBuilder id(String id) {
      this.id = id;
      return this;
    }

    @Override
    public WorkflowBuilder type(WorkflowMiniTypeField type) {
      this.type = new EnumWrapper<WorkflowMiniTypeField>(type);
      return this;
    }

    @Override
    public WorkflowBuilder type(EnumWrapper<WorkflowMiniTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public WorkflowBuilder name(String name) {
      this.name = name;
      return this;
    }

    @Override
    public WorkflowBuilder description(String description) {
      this.description = description;
      return this;
    }

    @Override
    public WorkflowBuilder isEnabled(Boolean isEnabled) {
      this.isEnabled = isEnabled;
      return this;
    }

    public Workflow build() {
      return new Workflow(this);
    }
  }
}
