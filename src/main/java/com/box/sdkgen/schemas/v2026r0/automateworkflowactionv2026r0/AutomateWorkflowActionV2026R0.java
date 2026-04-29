package com.box.sdkgen.schemas.v2026r0.automateworkflowactionv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.v2026r0.automateworkflowreferencev2026r0.AutomateWorkflowReferenceV2026R0;
import com.box.sdkgen.schemas.v2026r0.userminiv2026r0.UserMiniV2026R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

/** An Automate action that can start a workflow. */
@JsonFilter("nullablePropertyFilter")
public class AutomateWorkflowActionV2026R0 extends SerializableObject {

  /** The identifier for the Automate action. */
  protected final String id;

  /** The object type for this workflow action wrapper. */
  @JsonDeserialize(
      using =
          AutomateWorkflowActionV2026R0TypeField.AutomateWorkflowActionV2026R0TypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          AutomateWorkflowActionV2026R0TypeField.AutomateWorkflowActionV2026R0TypeFieldSerializer
              .class)
  protected EnumWrapper<AutomateWorkflowActionV2026R0TypeField> type;

  /** The type that defines the behavior of this action. */
  @JsonDeserialize(
      using =
          AutomateWorkflowActionV2026R0ActionTypeField
              .AutomateWorkflowActionV2026R0ActionTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          AutomateWorkflowActionV2026R0ActionTypeField
              .AutomateWorkflowActionV2026R0ActionTypeFieldSerializer.class)
  @JsonProperty("action_type")
  protected EnumWrapper<AutomateWorkflowActionV2026R0ActionTypeField> actionType;

  /** A human-readable description of the workflow action. */
  protected String description;

  /** The date and time when the action was created. */
  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime createdAt;

  /** The date and time when the action was last updated. */
  @JsonProperty("updated_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime updatedAt;

  @JsonProperty("created_by")
  protected UserMiniV2026R0 createdBy;

  @JsonProperty("updated_by")
  protected UserMiniV2026R0 updatedBy;

  protected final AutomateWorkflowReferenceV2026R0 workflow;

  public AutomateWorkflowActionV2026R0(
      @JsonProperty("id") String id,
      @JsonProperty("workflow") AutomateWorkflowReferenceV2026R0 workflow) {
    super();
    this.id = id;
    this.workflow = workflow;
    this.type =
        new EnumWrapper<AutomateWorkflowActionV2026R0TypeField>(
            AutomateWorkflowActionV2026R0TypeField.WORKFLOW_ACTION);
    this.actionType =
        new EnumWrapper<AutomateWorkflowActionV2026R0ActionTypeField>(
            AutomateWorkflowActionV2026R0ActionTypeField.RUN_WORKFLOW);
  }

  protected AutomateWorkflowActionV2026R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.actionType = builder.actionType;
    this.description = builder.description;
    this.createdAt = builder.createdAt;
    this.updatedAt = builder.updatedAt;
    this.createdBy = builder.createdBy;
    this.updatedBy = builder.updatedBy;
    this.workflow = builder.workflow;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<AutomateWorkflowActionV2026R0TypeField> getType() {
    return type;
  }

  public EnumWrapper<AutomateWorkflowActionV2026R0ActionTypeField> getActionType() {
    return actionType;
  }

  public String getDescription() {
    return description;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  public UserMiniV2026R0 getCreatedBy() {
    return createdBy;
  }

  public UserMiniV2026R0 getUpdatedBy() {
    return updatedBy;
  }

  public AutomateWorkflowReferenceV2026R0 getWorkflow() {
    return workflow;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AutomateWorkflowActionV2026R0 casted = (AutomateWorkflowActionV2026R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(actionType, casted.actionType)
        && Objects.equals(description, casted.description)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(updatedAt, casted.updatedAt)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(updatedBy, casted.updatedBy)
        && Objects.equals(workflow, casted.workflow);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, type, actionType, description, createdAt, updatedAt, createdBy, updatedBy, workflow);
  }

  @Override
  public String toString() {
    return "AutomateWorkflowActionV2026R0{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "actionType='"
        + actionType
        + '\''
        + ", "
        + "description='"
        + description
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "updatedAt='"
        + updatedAt
        + '\''
        + ", "
        + "createdBy='"
        + createdBy
        + '\''
        + ", "
        + "updatedBy='"
        + updatedBy
        + '\''
        + ", "
        + "workflow='"
        + workflow
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<AutomateWorkflowActionV2026R0TypeField> type;

    protected EnumWrapper<AutomateWorkflowActionV2026R0ActionTypeField> actionType;

    protected String description;

    protected OffsetDateTime createdAt;

    protected OffsetDateTime updatedAt;

    protected UserMiniV2026R0 createdBy;

    protected UserMiniV2026R0 updatedBy;

    protected final AutomateWorkflowReferenceV2026R0 workflow;

    public Builder(String id, AutomateWorkflowReferenceV2026R0 workflow) {
      super();
      this.id = id;
      this.workflow = workflow;
    }

    public Builder type(AutomateWorkflowActionV2026R0TypeField type) {
      this.type = new EnumWrapper<AutomateWorkflowActionV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<AutomateWorkflowActionV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public Builder actionType(AutomateWorkflowActionV2026R0ActionTypeField actionType) {
      this.actionType = new EnumWrapper<AutomateWorkflowActionV2026R0ActionTypeField>(actionType);
      return this;
    }

    public Builder actionType(
        EnumWrapper<AutomateWorkflowActionV2026R0ActionTypeField> actionType) {
      this.actionType = actionType;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder updatedAt(OffsetDateTime updatedAt) {
      this.updatedAt = updatedAt;
      return this;
    }

    public Builder createdBy(UserMiniV2026R0 createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public Builder updatedBy(UserMiniV2026R0 updatedBy) {
      this.updatedBy = updatedBy;
      return this;
    }

    public AutomateWorkflowActionV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<AutomateWorkflowActionV2026R0TypeField>(
                AutomateWorkflowActionV2026R0TypeField.WORKFLOW_ACTION);
      }
      if (this.actionType == null) {
        this.actionType =
            new EnumWrapper<AutomateWorkflowActionV2026R0ActionTypeField>(
                AutomateWorkflowActionV2026R0ActionTypeField.RUN_WORKFLOW);
      }
      return new AutomateWorkflowActionV2026R0(this);
    }
  }
}
