package com.box.sdkgen.schemas.workflow;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.userbase.UserBase;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class WorkflowFlowsField extends SerializableObject {

  /** The identifier of the flow. */
  protected String id;

  /** The flow's resource type. */
  @JsonDeserialize(using = WorkflowFlowsTypeField.WorkflowFlowsTypeFieldDeserializer.class)
  @JsonSerialize(using = WorkflowFlowsTypeField.WorkflowFlowsTypeFieldSerializer.class)
  protected EnumWrapper<WorkflowFlowsTypeField> type;

  protected WorkflowFlowsTriggerField trigger;

  protected List<WorkflowFlowsOutcomesField> outcomes;

  /** When this flow was created. */
  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime createdAt;

  @JsonProperty("created_by")
  protected UserBase createdBy;

  public WorkflowFlowsField() {
    super();
  }

  protected WorkflowFlowsField(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.trigger = builder.trigger;
    this.outcomes = builder.outcomes;
    this.createdAt = builder.createdAt;
    this.createdBy = builder.createdBy;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<WorkflowFlowsTypeField> getType() {
    return type;
  }

  public WorkflowFlowsTriggerField getTrigger() {
    return trigger;
  }

  public List<WorkflowFlowsOutcomesField> getOutcomes() {
    return outcomes;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public UserBase getCreatedBy() {
    return createdBy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WorkflowFlowsField casted = (WorkflowFlowsField) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(trigger, casted.trigger)
        && Objects.equals(outcomes, casted.outcomes)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(createdBy, casted.createdBy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, trigger, outcomes, createdAt, createdBy);
  }

  @Override
  public String toString() {
    return "WorkflowFlowsField{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "trigger='"
        + trigger
        + '\''
        + ", "
        + "outcomes='"
        + outcomes
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "createdBy='"
        + createdBy
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<WorkflowFlowsTypeField> type;

    protected WorkflowFlowsTriggerField trigger;

    protected List<WorkflowFlowsOutcomesField> outcomes;

    protected OffsetDateTime createdAt;

    protected UserBase createdBy;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(WorkflowFlowsTypeField type) {
      this.type = new EnumWrapper<WorkflowFlowsTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<WorkflowFlowsTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder trigger(WorkflowFlowsTriggerField trigger) {
      this.trigger = trigger;
      return this;
    }

    public Builder outcomes(List<WorkflowFlowsOutcomesField> outcomes) {
      this.outcomes = outcomes;
      return this;
    }

    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder createdBy(UserBase createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public WorkflowFlowsField build() {
      return new WorkflowFlowsField(this);
    }
  }
}
