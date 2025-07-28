package com.box.sdkgen.schemas.workflowfull;

import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.userbase.UserBase;
import com.box.sdkgen.schemas.workflow.Workflow;
import com.box.sdkgen.schemas.workflow.WorkflowFlowsField;
import com.box.sdkgen.schemas.workflowmini.WorkflowMiniTypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class WorkflowFull extends Workflow {

  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date createdAt;

  @JsonProperty("modified_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date modifiedAt;

  @JsonProperty("created_by")
  protected UserBase createdBy;

  @JsonProperty("modified_by")
  protected UserBase modifiedBy;

  public WorkflowFull() {
    super();
  }

  protected WorkflowFull(Builder builder) {
    super(builder);
    this.createdAt = builder.createdAt;
    this.modifiedAt = builder.modifiedAt;
    this.createdBy = builder.createdBy;
    this.modifiedBy = builder.modifiedBy;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public Date getModifiedAt() {
    return modifiedAt;
  }

  public UserBase getCreatedBy() {
    return createdBy;
  }

  public UserBase getModifiedBy() {
    return modifiedBy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WorkflowFull casted = (WorkflowFull) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name)
        && Objects.equals(description, casted.description)
        && Objects.equals(isEnabled, casted.isEnabled)
        && Objects.equals(flows, casted.flows)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(modifiedAt, casted.modifiedAt)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(modifiedBy, casted.modifiedBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        type,
        name,
        description,
        isEnabled,
        flows,
        createdAt,
        modifiedAt,
        createdBy,
        modifiedBy);
  }

  @Override
  public String toString() {
    return "WorkflowFull{"
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
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "modifiedAt='"
        + modifiedAt
        + '\''
        + ", "
        + "createdBy='"
        + createdBy
        + '\''
        + ", "
        + "modifiedBy='"
        + modifiedBy
        + '\''
        + "}";
  }

  public static class Builder extends Workflow.Builder {

    protected Date createdAt;

    protected Date modifiedAt;

    protected UserBase createdBy;

    protected UserBase modifiedBy;

    public Builder createdAt(Date createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder modifiedAt(Date modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public Builder createdBy(UserBase createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public Builder modifiedBy(UserBase modifiedBy) {
      this.modifiedBy = modifiedBy;
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

    @Override
    public Builder flows(List<WorkflowFlowsField> flows) {
      this.flows = flows;
      return this;
    }

    public WorkflowFull build() {
      return new WorkflowFull(this);
    }
  }
}
