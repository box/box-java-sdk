package com.box.sdkgen.schemas.v2026r0.shuttlejobschedulev2026r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.v2026r0.shuttlejobscheduletriggerbyjobv2026r0.ShuttleJobScheduleTriggerByJobV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlejobscheduletriggerbytimev2026r0.ShuttleJobScheduleTriggerByTimeV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlejobscheduletriggerv2026r0.ShuttleJobScheduleTriggerV2026R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * A schedule that starts runs of a shuttle job. A schedule is either time-based or triggered by
 * another job's run. Schedules cannot start a run in `scan` mode.
 */
@JsonFilter("nullablePropertyFilter")
public class ShuttleJobScheduleV2026R0 extends SerializableObject {

  /** The unique identifier for the shuttle job schedule. */
  protected final String id;

  /** The value will always be `shuttle_job_schedule`. */
  @JsonDeserialize(
      using =
          ShuttleJobScheduleV2026R0TypeField.ShuttleJobScheduleV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using = ShuttleJobScheduleV2026R0TypeField.ShuttleJobScheduleV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<ShuttleJobScheduleV2026R0TypeField> type;

  /** The unique identifier of the shuttle job this schedule belongs to. */
  @JsonProperty("job_id")
  protected final String jobId;

  /** Whether the schedule is active. */
  @JsonDeserialize(
      using =
          ShuttleJobScheduleV2026R0StateField.ShuttleJobScheduleV2026R0StateFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShuttleJobScheduleV2026R0StateField.ShuttleJobScheduleV2026R0StateFieldSerializer.class)
  protected final EnumWrapper<ShuttleJobScheduleV2026R0StateField> state;

  /** The kind of run the schedule starts. `scan` is not a valid schedule run mode. */
  @JsonDeserialize(
      using =
          ShuttleJobScheduleV2026R0RunModeField.ShuttleJobScheduleV2026R0RunModeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          ShuttleJobScheduleV2026R0RunModeField.ShuttleJobScheduleV2026R0RunModeFieldSerializer
              .class)
  @JsonProperty("run_mode")
  protected final EnumWrapper<ShuttleJobScheduleV2026R0RunModeField> runMode;

  /** An optional human-readable description of the schedule. */
  @Nullable protected String description;

  /**
   * When the schedule last attempted to start a run. `null` when the schedule has never started a
   * run.
   */
  @JsonProperty("last_attempt_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  @Nullable
  protected OffsetDateTime lastAttemptAt;

  protected final ShuttleJobScheduleTriggerV2026R0 trigger;

  public ShuttleJobScheduleV2026R0(
      String id,
      String jobId,
      ShuttleJobScheduleV2026R0StateField state,
      ShuttleJobScheduleV2026R0RunModeField runMode,
      ShuttleJobScheduleTriggerByTimeV2026R0 trigger) {
    super();
    this.id = id;
    this.jobId = jobId;
    this.state = new EnumWrapper<ShuttleJobScheduleV2026R0StateField>(state);
    this.runMode = new EnumWrapper<ShuttleJobScheduleV2026R0RunModeField>(runMode);
    this.trigger = new ShuttleJobScheduleTriggerV2026R0(trigger);
    this.type =
        new EnumWrapper<ShuttleJobScheduleV2026R0TypeField>(
            ShuttleJobScheduleV2026R0TypeField.SHUTTLE_JOB_SCHEDULE);
  }

  public ShuttleJobScheduleV2026R0(
      String id,
      String jobId,
      ShuttleJobScheduleV2026R0StateField state,
      ShuttleJobScheduleV2026R0RunModeField runMode,
      ShuttleJobScheduleTriggerByJobV2026R0 trigger) {
    super();
    this.id = id;
    this.jobId = jobId;
    this.state = new EnumWrapper<ShuttleJobScheduleV2026R0StateField>(state);
    this.runMode = new EnumWrapper<ShuttleJobScheduleV2026R0RunModeField>(runMode);
    this.trigger = new ShuttleJobScheduleTriggerV2026R0(trigger);
    this.type =
        new EnumWrapper<ShuttleJobScheduleV2026R0TypeField>(
            ShuttleJobScheduleV2026R0TypeField.SHUTTLE_JOB_SCHEDULE);
  }

  public ShuttleJobScheduleV2026R0(
      String id,
      String jobId,
      ShuttleJobScheduleV2026R0StateField state,
      ShuttleJobScheduleV2026R0RunModeField runMode,
      ShuttleJobScheduleTriggerV2026R0 trigger) {
    super();
    this.id = id;
    this.jobId = jobId;
    this.state = new EnumWrapper<ShuttleJobScheduleV2026R0StateField>(state);
    this.runMode = new EnumWrapper<ShuttleJobScheduleV2026R0RunModeField>(runMode);
    this.trigger = trigger;
    this.type =
        new EnumWrapper<ShuttleJobScheduleV2026R0TypeField>(
            ShuttleJobScheduleV2026R0TypeField.SHUTTLE_JOB_SCHEDULE);
  }

  public ShuttleJobScheduleV2026R0(
      String id,
      String jobId,
      ShuttleJobScheduleV2026R0StateField state,
      EnumWrapper<ShuttleJobScheduleV2026R0RunModeField> runMode,
      ShuttleJobScheduleTriggerByTimeV2026R0 trigger) {
    super();
    this.id = id;
    this.jobId = jobId;
    this.state = new EnumWrapper<ShuttleJobScheduleV2026R0StateField>(state);
    this.runMode = runMode;
    this.trigger = new ShuttleJobScheduleTriggerV2026R0(trigger);
    this.type =
        new EnumWrapper<ShuttleJobScheduleV2026R0TypeField>(
            ShuttleJobScheduleV2026R0TypeField.SHUTTLE_JOB_SCHEDULE);
  }

  public ShuttleJobScheduleV2026R0(
      String id,
      String jobId,
      ShuttleJobScheduleV2026R0StateField state,
      EnumWrapper<ShuttleJobScheduleV2026R0RunModeField> runMode,
      ShuttleJobScheduleTriggerByJobV2026R0 trigger) {
    super();
    this.id = id;
    this.jobId = jobId;
    this.state = new EnumWrapper<ShuttleJobScheduleV2026R0StateField>(state);
    this.runMode = runMode;
    this.trigger = new ShuttleJobScheduleTriggerV2026R0(trigger);
    this.type =
        new EnumWrapper<ShuttleJobScheduleV2026R0TypeField>(
            ShuttleJobScheduleV2026R0TypeField.SHUTTLE_JOB_SCHEDULE);
  }

  public ShuttleJobScheduleV2026R0(
      String id,
      String jobId,
      ShuttleJobScheduleV2026R0StateField state,
      EnumWrapper<ShuttleJobScheduleV2026R0RunModeField> runMode,
      ShuttleJobScheduleTriggerV2026R0 trigger) {
    super();
    this.id = id;
    this.jobId = jobId;
    this.state = new EnumWrapper<ShuttleJobScheduleV2026R0StateField>(state);
    this.runMode = runMode;
    this.trigger = trigger;
    this.type =
        new EnumWrapper<ShuttleJobScheduleV2026R0TypeField>(
            ShuttleJobScheduleV2026R0TypeField.SHUTTLE_JOB_SCHEDULE);
  }

  public ShuttleJobScheduleV2026R0(
      String id,
      String jobId,
      EnumWrapper<ShuttleJobScheduleV2026R0StateField> state,
      ShuttleJobScheduleV2026R0RunModeField runMode,
      ShuttleJobScheduleTriggerByTimeV2026R0 trigger) {
    super();
    this.id = id;
    this.jobId = jobId;
    this.state = state;
    this.runMode = new EnumWrapper<ShuttleJobScheduleV2026R0RunModeField>(runMode);
    this.trigger = new ShuttleJobScheduleTriggerV2026R0(trigger);
    this.type =
        new EnumWrapper<ShuttleJobScheduleV2026R0TypeField>(
            ShuttleJobScheduleV2026R0TypeField.SHUTTLE_JOB_SCHEDULE);
  }

  public ShuttleJobScheduleV2026R0(
      String id,
      String jobId,
      EnumWrapper<ShuttleJobScheduleV2026R0StateField> state,
      ShuttleJobScheduleV2026R0RunModeField runMode,
      ShuttleJobScheduleTriggerByJobV2026R0 trigger) {
    super();
    this.id = id;
    this.jobId = jobId;
    this.state = state;
    this.runMode = new EnumWrapper<ShuttleJobScheduleV2026R0RunModeField>(runMode);
    this.trigger = new ShuttleJobScheduleTriggerV2026R0(trigger);
    this.type =
        new EnumWrapper<ShuttleJobScheduleV2026R0TypeField>(
            ShuttleJobScheduleV2026R0TypeField.SHUTTLE_JOB_SCHEDULE);
  }

  public ShuttleJobScheduleV2026R0(
      String id,
      String jobId,
      EnumWrapper<ShuttleJobScheduleV2026R0StateField> state,
      ShuttleJobScheduleV2026R0RunModeField runMode,
      ShuttleJobScheduleTriggerV2026R0 trigger) {
    super();
    this.id = id;
    this.jobId = jobId;
    this.state = state;
    this.runMode = new EnumWrapper<ShuttleJobScheduleV2026R0RunModeField>(runMode);
    this.trigger = trigger;
    this.type =
        new EnumWrapper<ShuttleJobScheduleV2026R0TypeField>(
            ShuttleJobScheduleV2026R0TypeField.SHUTTLE_JOB_SCHEDULE);
  }

  public ShuttleJobScheduleV2026R0(
      String id,
      String jobId,
      EnumWrapper<ShuttleJobScheduleV2026R0StateField> state,
      EnumWrapper<ShuttleJobScheduleV2026R0RunModeField> runMode,
      ShuttleJobScheduleTriggerByTimeV2026R0 trigger) {
    super();
    this.id = id;
    this.jobId = jobId;
    this.state = state;
    this.runMode = runMode;
    this.trigger = new ShuttleJobScheduleTriggerV2026R0(trigger);
    this.type =
        new EnumWrapper<ShuttleJobScheduleV2026R0TypeField>(
            ShuttleJobScheduleV2026R0TypeField.SHUTTLE_JOB_SCHEDULE);
  }

  public ShuttleJobScheduleV2026R0(
      String id,
      String jobId,
      EnumWrapper<ShuttleJobScheduleV2026R0StateField> state,
      EnumWrapper<ShuttleJobScheduleV2026R0RunModeField> runMode,
      ShuttleJobScheduleTriggerByJobV2026R0 trigger) {
    super();
    this.id = id;
    this.jobId = jobId;
    this.state = state;
    this.runMode = runMode;
    this.trigger = new ShuttleJobScheduleTriggerV2026R0(trigger);
    this.type =
        new EnumWrapper<ShuttleJobScheduleV2026R0TypeField>(
            ShuttleJobScheduleV2026R0TypeField.SHUTTLE_JOB_SCHEDULE);
  }

  public ShuttleJobScheduleV2026R0(
      @JsonProperty("id") String id,
      @JsonProperty("job_id") String jobId,
      @JsonProperty("state") EnumWrapper<ShuttleJobScheduleV2026R0StateField> state,
      @JsonProperty("run_mode") EnumWrapper<ShuttleJobScheduleV2026R0RunModeField> runMode,
      @JsonProperty("trigger") ShuttleJobScheduleTriggerV2026R0 trigger) {
    super();
    this.id = id;
    this.jobId = jobId;
    this.state = state;
    this.runMode = runMode;
    this.trigger = trigger;
    this.type =
        new EnumWrapper<ShuttleJobScheduleV2026R0TypeField>(
            ShuttleJobScheduleV2026R0TypeField.SHUTTLE_JOB_SCHEDULE);
  }

  protected ShuttleJobScheduleV2026R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.jobId = builder.jobId;
    this.state = builder.state;
    this.runMode = builder.runMode;
    this.description = builder.description;
    this.lastAttemptAt = builder.lastAttemptAt;
    this.trigger = builder.trigger;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<ShuttleJobScheduleV2026R0TypeField> getType() {
    return type;
  }

  public String getJobId() {
    return jobId;
  }

  public EnumWrapper<ShuttleJobScheduleV2026R0StateField> getState() {
    return state;
  }

  public EnumWrapper<ShuttleJobScheduleV2026R0RunModeField> getRunMode() {
    return runMode;
  }

  public String getDescription() {
    return description;
  }

  public OffsetDateTime getLastAttemptAt() {
    return lastAttemptAt;
  }

  public ShuttleJobScheduleTriggerV2026R0 getTrigger() {
    return trigger;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttleJobScheduleV2026R0 casted = (ShuttleJobScheduleV2026R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(jobId, casted.jobId)
        && Objects.equals(state, casted.state)
        && Objects.equals(runMode, casted.runMode)
        && Objects.equals(description, casted.description)
        && Objects.equals(lastAttemptAt, casted.lastAttemptAt)
        && Objects.equals(trigger, casted.trigger);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, jobId, state, runMode, description, lastAttemptAt, trigger);
  }

  @Override
  public String toString() {
    return "ShuttleJobScheduleV2026R0{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "jobId='"
        + jobId
        + '\''
        + ", "
        + "state='"
        + state
        + '\''
        + ", "
        + "runMode='"
        + runMode
        + '\''
        + ", "
        + "description='"
        + description
        + '\''
        + ", "
        + "lastAttemptAt='"
        + lastAttemptAt
        + '\''
        + ", "
        + "trigger='"
        + trigger
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<ShuttleJobScheduleV2026R0TypeField> type;

    protected final String jobId;

    protected final EnumWrapper<ShuttleJobScheduleV2026R0StateField> state;

    protected final EnumWrapper<ShuttleJobScheduleV2026R0RunModeField> runMode;

    protected String description;

    protected OffsetDateTime lastAttemptAt;

    protected final ShuttleJobScheduleTriggerV2026R0 trigger;

    public Builder(
        String id,
        String jobId,
        ShuttleJobScheduleV2026R0StateField state,
        ShuttleJobScheduleV2026R0RunModeField runMode,
        ShuttleJobScheduleTriggerByTimeV2026R0 trigger) {
      super();
      this.id = id;
      this.jobId = jobId;
      this.state = new EnumWrapper<ShuttleJobScheduleV2026R0StateField>(state);
      this.runMode = new EnumWrapper<ShuttleJobScheduleV2026R0RunModeField>(runMode);
      this.trigger = new ShuttleJobScheduleTriggerV2026R0(trigger);
    }

    public Builder(
        String id,
        String jobId,
        ShuttleJobScheduleV2026R0StateField state,
        ShuttleJobScheduleV2026R0RunModeField runMode,
        ShuttleJobScheduleTriggerByJobV2026R0 trigger) {
      super();
      this.id = id;
      this.jobId = jobId;
      this.state = new EnumWrapper<ShuttleJobScheduleV2026R0StateField>(state);
      this.runMode = new EnumWrapper<ShuttleJobScheduleV2026R0RunModeField>(runMode);
      this.trigger = new ShuttleJobScheduleTriggerV2026R0(trigger);
    }

    public Builder(
        String id,
        String jobId,
        ShuttleJobScheduleV2026R0StateField state,
        ShuttleJobScheduleV2026R0RunModeField runMode,
        ShuttleJobScheduleTriggerV2026R0 trigger) {
      super();
      this.id = id;
      this.jobId = jobId;
      this.state = new EnumWrapper<ShuttleJobScheduleV2026R0StateField>(state);
      this.runMode = new EnumWrapper<ShuttleJobScheduleV2026R0RunModeField>(runMode);
      this.trigger = trigger;
    }

    public Builder(
        String id,
        String jobId,
        ShuttleJobScheduleV2026R0StateField state,
        EnumWrapper<ShuttleJobScheduleV2026R0RunModeField> runMode,
        ShuttleJobScheduleTriggerByTimeV2026R0 trigger) {
      super();
      this.id = id;
      this.jobId = jobId;
      this.state = new EnumWrapper<ShuttleJobScheduleV2026R0StateField>(state);
      this.runMode = runMode;
      this.trigger = new ShuttleJobScheduleTriggerV2026R0(trigger);
    }

    public Builder(
        String id,
        String jobId,
        ShuttleJobScheduleV2026R0StateField state,
        EnumWrapper<ShuttleJobScheduleV2026R0RunModeField> runMode,
        ShuttleJobScheduleTriggerByJobV2026R0 trigger) {
      super();
      this.id = id;
      this.jobId = jobId;
      this.state = new EnumWrapper<ShuttleJobScheduleV2026R0StateField>(state);
      this.runMode = runMode;
      this.trigger = new ShuttleJobScheduleTriggerV2026R0(trigger);
    }

    public Builder(
        String id,
        String jobId,
        ShuttleJobScheduleV2026R0StateField state,
        EnumWrapper<ShuttleJobScheduleV2026R0RunModeField> runMode,
        ShuttleJobScheduleTriggerV2026R0 trigger) {
      super();
      this.id = id;
      this.jobId = jobId;
      this.state = new EnumWrapper<ShuttleJobScheduleV2026R0StateField>(state);
      this.runMode = runMode;
      this.trigger = trigger;
    }

    public Builder(
        String id,
        String jobId,
        EnumWrapper<ShuttleJobScheduleV2026R0StateField> state,
        ShuttleJobScheduleV2026R0RunModeField runMode,
        ShuttleJobScheduleTriggerByTimeV2026R0 trigger) {
      super();
      this.id = id;
      this.jobId = jobId;
      this.state = state;
      this.runMode = new EnumWrapper<ShuttleJobScheduleV2026R0RunModeField>(runMode);
      this.trigger = new ShuttleJobScheduleTriggerV2026R0(trigger);
    }

    public Builder(
        String id,
        String jobId,
        EnumWrapper<ShuttleJobScheduleV2026R0StateField> state,
        ShuttleJobScheduleV2026R0RunModeField runMode,
        ShuttleJobScheduleTriggerByJobV2026R0 trigger) {
      super();
      this.id = id;
      this.jobId = jobId;
      this.state = state;
      this.runMode = new EnumWrapper<ShuttleJobScheduleV2026R0RunModeField>(runMode);
      this.trigger = new ShuttleJobScheduleTriggerV2026R0(trigger);
    }

    public Builder(
        String id,
        String jobId,
        EnumWrapper<ShuttleJobScheduleV2026R0StateField> state,
        ShuttleJobScheduleV2026R0RunModeField runMode,
        ShuttleJobScheduleTriggerV2026R0 trigger) {
      super();
      this.id = id;
      this.jobId = jobId;
      this.state = state;
      this.runMode = new EnumWrapper<ShuttleJobScheduleV2026R0RunModeField>(runMode);
      this.trigger = trigger;
    }

    public Builder(
        String id,
        String jobId,
        EnumWrapper<ShuttleJobScheduleV2026R0StateField> state,
        EnumWrapper<ShuttleJobScheduleV2026R0RunModeField> runMode,
        ShuttleJobScheduleTriggerByTimeV2026R0 trigger) {
      super();
      this.id = id;
      this.jobId = jobId;
      this.state = state;
      this.runMode = runMode;
      this.trigger = new ShuttleJobScheduleTriggerV2026R0(trigger);
    }

    public Builder(
        String id,
        String jobId,
        EnumWrapper<ShuttleJobScheduleV2026R0StateField> state,
        EnumWrapper<ShuttleJobScheduleV2026R0RunModeField> runMode,
        ShuttleJobScheduleTriggerByJobV2026R0 trigger) {
      super();
      this.id = id;
      this.jobId = jobId;
      this.state = state;
      this.runMode = runMode;
      this.trigger = new ShuttleJobScheduleTriggerV2026R0(trigger);
    }

    public Builder(
        String id,
        String jobId,
        EnumWrapper<ShuttleJobScheduleV2026R0StateField> state,
        EnumWrapper<ShuttleJobScheduleV2026R0RunModeField> runMode,
        ShuttleJobScheduleTriggerV2026R0 trigger) {
      super();
      this.id = id;
      this.jobId = jobId;
      this.state = state;
      this.runMode = runMode;
      this.trigger = trigger;
    }

    public Builder type(ShuttleJobScheduleV2026R0TypeField type) {
      this.type = new EnumWrapper<ShuttleJobScheduleV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShuttleJobScheduleV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      this.markNullableFieldAsSet("description");
      return this;
    }

    public Builder lastAttemptAt(OffsetDateTime lastAttemptAt) {
      this.lastAttemptAt = lastAttemptAt;
      this.markNullableFieldAsSet("last_attempt_at");
      return this;
    }

    public ShuttleJobScheduleV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<ShuttleJobScheduleV2026R0TypeField>(
                ShuttleJobScheduleV2026R0TypeField.SHUTTLE_JOB_SCHEDULE);
      }
      return new ShuttleJobScheduleV2026R0(this);
    }
  }
}
