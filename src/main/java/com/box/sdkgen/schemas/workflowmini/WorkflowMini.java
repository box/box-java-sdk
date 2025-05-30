package com.box.sdkgen.schemas.workflowmini;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class WorkflowMini extends SerializableObject {

  protected String id;

  @JsonDeserialize(using = WorkflowMiniTypeField.WorkflowMiniTypeFieldDeserializer.class)
  @JsonSerialize(using = WorkflowMiniTypeField.WorkflowMiniTypeFieldSerializer.class)
  protected EnumWrapper<WorkflowMiniTypeField> type;

  protected String name;

  protected String description;

  @JsonProperty("is_enabled")
  protected Boolean isEnabled;

  public WorkflowMini() {
    super();
  }

  protected WorkflowMini(WorkflowMiniBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.name = builder.name;
    this.description = builder.description;
    this.isEnabled = builder.isEnabled;
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<WorkflowMiniTypeField> getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public Boolean getIsEnabled() {
    return isEnabled;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WorkflowMini casted = (WorkflowMini) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name)
        && Objects.equals(description, casted.description)
        && Objects.equals(isEnabled, casted.isEnabled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name, description, isEnabled);
  }

  @Override
  public String toString() {
    return "WorkflowMini{"
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
        + "}";
  }

  public static class WorkflowMiniBuilder {

    protected String id;

    protected EnumWrapper<WorkflowMiniTypeField> type;

    protected String name;

    protected String description;

    protected Boolean isEnabled;

    public WorkflowMiniBuilder id(String id) {
      this.id = id;
      return this;
    }

    public WorkflowMiniBuilder type(WorkflowMiniTypeField type) {
      this.type = new EnumWrapper<WorkflowMiniTypeField>(type);
      return this;
    }

    public WorkflowMiniBuilder type(EnumWrapper<WorkflowMiniTypeField> type) {
      this.type = type;
      return this;
    }

    public WorkflowMiniBuilder name(String name) {
      this.name = name;
      return this;
    }

    public WorkflowMiniBuilder description(String description) {
      this.description = description;
      return this;
    }

    public WorkflowMiniBuilder isEnabled(Boolean isEnabled) {
      this.isEnabled = isEnabled;
      return this;
    }

    public WorkflowMini build() {
      return new WorkflowMini(this);
    }
  }
}
