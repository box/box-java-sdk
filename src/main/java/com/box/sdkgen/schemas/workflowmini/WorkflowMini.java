package com.box.sdkgen.schemas.workflowmini;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * Box Relay Workflows are objects that represent a named collection of flows.
 *
 * <p>You application must be authorized to use the `Manage Box Relay` application scope within the
 * developer console in order to use this resource.
 */
@JsonFilter("nullablePropertyFilter")
public class WorkflowMini extends SerializableObject {

  /** The unique identifier for the workflow. */
  protected String id;

  /** The value will always be `workflow`. */
  @JsonDeserialize(using = WorkflowMiniTypeField.WorkflowMiniTypeFieldDeserializer.class)
  @JsonSerialize(using = WorkflowMiniTypeField.WorkflowMiniTypeFieldSerializer.class)
  protected EnumWrapper<WorkflowMiniTypeField> type;

  /** The name of the workflow. */
  protected String name;

  /** The description for a workflow. */
  protected String description;

  /** Specifies if this workflow is enabled. */
  @JsonProperty("is_enabled")
  protected Boolean isEnabled;

  public WorkflowMini() {
    super();
  }

  protected WorkflowMini(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.name = builder.name;
    this.description = builder.description;
    this.isEnabled = builder.isEnabled;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
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

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<WorkflowMiniTypeField> type;

    protected String name;

    protected String description;

    protected Boolean isEnabled;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(WorkflowMiniTypeField type) {
      this.type = new EnumWrapper<WorkflowMiniTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<WorkflowMiniTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder isEnabled(Boolean isEnabled) {
      this.isEnabled = isEnabled;
      return this;
    }

    public WorkflowMini build() {
      return new WorkflowMini(this);
    }
  }
}
