package com.box.sdkgen.managers.workflows;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class StartWorkflowRequestBodyFlowField extends SerializableObject {

  protected String type;

  protected String id;

  public StartWorkflowRequestBodyFlowField() {
    super();
  }

  protected StartWorkflowRequestBodyFlowField(StartWorkflowRequestBodyFlowFieldBuilder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
  }

  public String getType() {
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
    StartWorkflowRequestBodyFlowField casted = (StartWorkflowRequestBodyFlowField) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "StartWorkflowRequestBodyFlowField{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + "}";
  }

  public static class StartWorkflowRequestBodyFlowFieldBuilder {

    protected String type;

    protected String id;

    public StartWorkflowRequestBodyFlowFieldBuilder type(String type) {
      this.type = type;
      return this;
    }

    public StartWorkflowRequestBodyFlowFieldBuilder id(String id) {
      this.id = id;
      return this;
    }

    public StartWorkflowRequestBodyFlowField build() {
      return new StartWorkflowRequestBodyFlowField(this);
    }
  }
}
