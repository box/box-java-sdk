package com.box.sdkgen.schemas.v2026r0.shuttlejobscheduletriggerbyjobv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * A job-based trigger that starts this schedule after another shuttle job's run meets a condition.
 */
@JsonFilter("nullablePropertyFilter")
public class ShuttleJobScheduleTriggerByJobV2026R0 extends SerializableObject {

  /** The value will always be `by_job`. */
  @JsonDeserialize(
      using =
          ShuttleJobScheduleTriggerByJobV2026R0TypeField
              .ShuttleJobScheduleTriggerByJobV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShuttleJobScheduleTriggerByJobV2026R0TypeField
              .ShuttleJobScheduleTriggerByJobV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<ShuttleJobScheduleTriggerByJobV2026R0TypeField> type;

  /** The unique identifier of the shuttle job whose run starts this schedule. */
  @JsonProperty("triggered_by_job_id")
  protected final String triggeredByJobId;

  /** When this schedule should start a run relative to the last run of the triggering job. */
  @JsonDeserialize(
      using =
          ShuttleJobScheduleTriggerByJobV2026R0LastRunConditionField
              .ShuttleJobScheduleTriggerByJobV2026R0LastRunConditionFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShuttleJobScheduleTriggerByJobV2026R0LastRunConditionField
              .ShuttleJobScheduleTriggerByJobV2026R0LastRunConditionFieldSerializer.class)
  @JsonProperty("last_run_condition")
  protected final EnumWrapper<ShuttleJobScheduleTriggerByJobV2026R0LastRunConditionField>
      lastRunCondition;

  public ShuttleJobScheduleTriggerByJobV2026R0(
      String triggeredByJobId,
      ShuttleJobScheduleTriggerByJobV2026R0LastRunConditionField lastRunCondition) {
    super();
    this.triggeredByJobId = triggeredByJobId;
    this.lastRunCondition =
        new EnumWrapper<ShuttleJobScheduleTriggerByJobV2026R0LastRunConditionField>(
            lastRunCondition);
    this.type =
        new EnumWrapper<ShuttleJobScheduleTriggerByJobV2026R0TypeField>(
            ShuttleJobScheduleTriggerByJobV2026R0TypeField.BY_JOB);
  }

  public ShuttleJobScheduleTriggerByJobV2026R0(
      @JsonProperty("triggered_by_job_id") String triggeredByJobId,
      @JsonProperty("last_run_condition")
          EnumWrapper<ShuttleJobScheduleTriggerByJobV2026R0LastRunConditionField>
              lastRunCondition) {
    super();
    this.triggeredByJobId = triggeredByJobId;
    this.lastRunCondition = lastRunCondition;
    this.type =
        new EnumWrapper<ShuttleJobScheduleTriggerByJobV2026R0TypeField>(
            ShuttleJobScheduleTriggerByJobV2026R0TypeField.BY_JOB);
  }

  protected ShuttleJobScheduleTriggerByJobV2026R0(Builder builder) {
    super();
    this.type = builder.type;
    this.triggeredByJobId = builder.triggeredByJobId;
    this.lastRunCondition = builder.lastRunCondition;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<ShuttleJobScheduleTriggerByJobV2026R0TypeField> getType() {
    return type;
  }

  public String getTriggeredByJobId() {
    return triggeredByJobId;
  }

  public EnumWrapper<ShuttleJobScheduleTriggerByJobV2026R0LastRunConditionField>
      getLastRunCondition() {
    return lastRunCondition;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttleJobScheduleTriggerByJobV2026R0 casted = (ShuttleJobScheduleTriggerByJobV2026R0) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(triggeredByJobId, casted.triggeredByJobId)
        && Objects.equals(lastRunCondition, casted.lastRunCondition);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, triggeredByJobId, lastRunCondition);
  }

  @Override
  public String toString() {
    return "ShuttleJobScheduleTriggerByJobV2026R0{"
        + "type='"
        + type
        + '\''
        + ", "
        + "triggeredByJobId='"
        + triggeredByJobId
        + '\''
        + ", "
        + "lastRunCondition='"
        + lastRunCondition
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<ShuttleJobScheduleTriggerByJobV2026R0TypeField> type;

    protected final String triggeredByJobId;

    protected final EnumWrapper<ShuttleJobScheduleTriggerByJobV2026R0LastRunConditionField>
        lastRunCondition;

    public Builder(
        String triggeredByJobId,
        ShuttleJobScheduleTriggerByJobV2026R0LastRunConditionField lastRunCondition) {
      super();
      this.triggeredByJobId = triggeredByJobId;
      this.lastRunCondition =
          new EnumWrapper<ShuttleJobScheduleTriggerByJobV2026R0LastRunConditionField>(
              lastRunCondition);
    }

    public Builder(
        String triggeredByJobId,
        EnumWrapper<ShuttleJobScheduleTriggerByJobV2026R0LastRunConditionField> lastRunCondition) {
      super();
      this.triggeredByJobId = triggeredByJobId;
      this.lastRunCondition = lastRunCondition;
    }

    public Builder type(ShuttleJobScheduleTriggerByJobV2026R0TypeField type) {
      this.type = new EnumWrapper<ShuttleJobScheduleTriggerByJobV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShuttleJobScheduleTriggerByJobV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public ShuttleJobScheduleTriggerByJobV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<ShuttleJobScheduleTriggerByJobV2026R0TypeField>(
                ShuttleJobScheduleTriggerByJobV2026R0TypeField.BY_JOB);
      }
      return new ShuttleJobScheduleTriggerByJobV2026R0(this);
    }
  }
}
