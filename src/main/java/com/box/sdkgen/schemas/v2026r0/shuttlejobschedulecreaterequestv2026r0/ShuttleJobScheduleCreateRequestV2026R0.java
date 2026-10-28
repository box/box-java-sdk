package com.box.sdkgen.schemas.v2026r0.shuttlejobschedulecreaterequestv2026r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2026r0.shuttlejobscheduletriggerbyjobv2026r0.ShuttleJobScheduleTriggerByJobV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlejobscheduletriggerbytimev2026r0.ShuttleJobScheduleTriggerByTimeV2026R0;
import com.box.sdkgen.schemas.v2026r0.shuttlejobscheduletriggerv2026r0.ShuttleJobScheduleTriggerV2026R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** The request body used to create a shuttle job schedule. */
@JsonFilter("nullablePropertyFilter")
public class ShuttleJobScheduleCreateRequestV2026R0 extends SerializableObject {

  /** The unique identifier of the shuttle job this schedule belongs to. */
  @JsonProperty("job_id")
  protected final String jobId;

  /** The kind of run the schedule starts. `scan` is not a valid schedule run mode. */
  @JsonDeserialize(
      using =
          ShuttleJobScheduleCreateRequestV2026R0RunModeField
              .ShuttleJobScheduleCreateRequestV2026R0RunModeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShuttleJobScheduleCreateRequestV2026R0RunModeField
              .ShuttleJobScheduleCreateRequestV2026R0RunModeFieldSerializer.class)
  @JsonProperty("run_mode")
  protected final EnumWrapper<ShuttleJobScheduleCreateRequestV2026R0RunModeField> runMode;

  /** Whether the schedule is active. When omitted, defaults to `enabled`. */
  @JsonDeserialize(
      using =
          ShuttleJobScheduleCreateRequestV2026R0StateField
              .ShuttleJobScheduleCreateRequestV2026R0StateFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShuttleJobScheduleCreateRequestV2026R0StateField
              .ShuttleJobScheduleCreateRequestV2026R0StateFieldSerializer.class)
  protected EnumWrapper<ShuttleJobScheduleCreateRequestV2026R0StateField> state;

  /** An optional human-readable description of the schedule. */
  @Nullable protected String description;

  protected final ShuttleJobScheduleTriggerV2026R0 trigger;

  public ShuttleJobScheduleCreateRequestV2026R0(
      String jobId,
      ShuttleJobScheduleCreateRequestV2026R0RunModeField runMode,
      ShuttleJobScheduleTriggerByTimeV2026R0 trigger) {
    super();
    this.jobId = jobId;
    this.runMode = new EnumWrapper<ShuttleJobScheduleCreateRequestV2026R0RunModeField>(runMode);
    this.trigger = new ShuttleJobScheduleTriggerV2026R0(trigger);
  }

  public ShuttleJobScheduleCreateRequestV2026R0(
      String jobId,
      ShuttleJobScheduleCreateRequestV2026R0RunModeField runMode,
      ShuttleJobScheduleTriggerByJobV2026R0 trigger) {
    super();
    this.jobId = jobId;
    this.runMode = new EnumWrapper<ShuttleJobScheduleCreateRequestV2026R0RunModeField>(runMode);
    this.trigger = new ShuttleJobScheduleTriggerV2026R0(trigger);
  }

  public ShuttleJobScheduleCreateRequestV2026R0(
      String jobId,
      ShuttleJobScheduleCreateRequestV2026R0RunModeField runMode,
      ShuttleJobScheduleTriggerV2026R0 trigger) {
    super();
    this.jobId = jobId;
    this.runMode = new EnumWrapper<ShuttleJobScheduleCreateRequestV2026R0RunModeField>(runMode);
    this.trigger = trigger;
  }

  public ShuttleJobScheduleCreateRequestV2026R0(
      String jobId,
      EnumWrapper<ShuttleJobScheduleCreateRequestV2026R0RunModeField> runMode,
      ShuttleJobScheduleTriggerByTimeV2026R0 trigger) {
    super();
    this.jobId = jobId;
    this.runMode = runMode;
    this.trigger = new ShuttleJobScheduleTriggerV2026R0(trigger);
  }

  public ShuttleJobScheduleCreateRequestV2026R0(
      String jobId,
      EnumWrapper<ShuttleJobScheduleCreateRequestV2026R0RunModeField> runMode,
      ShuttleJobScheduleTriggerByJobV2026R0 trigger) {
    super();
    this.jobId = jobId;
    this.runMode = runMode;
    this.trigger = new ShuttleJobScheduleTriggerV2026R0(trigger);
  }

  public ShuttleJobScheduleCreateRequestV2026R0(
      @JsonProperty("job_id") String jobId,
      @JsonProperty("run_mode")
          EnumWrapper<ShuttleJobScheduleCreateRequestV2026R0RunModeField> runMode,
      @JsonProperty("trigger") ShuttleJobScheduleTriggerV2026R0 trigger) {
    super();
    this.jobId = jobId;
    this.runMode = runMode;
    this.trigger = trigger;
  }

  protected ShuttleJobScheduleCreateRequestV2026R0(Builder builder) {
    super();
    this.jobId = builder.jobId;
    this.runMode = builder.runMode;
    this.state = builder.state;
    this.description = builder.description;
    this.trigger = builder.trigger;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getJobId() {
    return jobId;
  }

  public EnumWrapper<ShuttleJobScheduleCreateRequestV2026R0RunModeField> getRunMode() {
    return runMode;
  }

  public EnumWrapper<ShuttleJobScheduleCreateRequestV2026R0StateField> getState() {
    return state;
  }

  public String getDescription() {
    return description;
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
    ShuttleJobScheduleCreateRequestV2026R0 casted = (ShuttleJobScheduleCreateRequestV2026R0) o;
    return Objects.equals(jobId, casted.jobId)
        && Objects.equals(runMode, casted.runMode)
        && Objects.equals(state, casted.state)
        && Objects.equals(description, casted.description)
        && Objects.equals(trigger, casted.trigger);
  }

  @Override
  public int hashCode() {
    return Objects.hash(jobId, runMode, state, description, trigger);
  }

  @Override
  public String toString() {
    return "ShuttleJobScheduleCreateRequestV2026R0{"
        + "jobId='"
        + jobId
        + '\''
        + ", "
        + "runMode='"
        + runMode
        + '\''
        + ", "
        + "state='"
        + state
        + '\''
        + ", "
        + "description='"
        + description
        + '\''
        + ", "
        + "trigger='"
        + trigger
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String jobId;

    protected final EnumWrapper<ShuttleJobScheduleCreateRequestV2026R0RunModeField> runMode;

    protected EnumWrapper<ShuttleJobScheduleCreateRequestV2026R0StateField> state;

    protected String description;

    protected final ShuttleJobScheduleTriggerV2026R0 trigger;

    public Builder(
        String jobId,
        ShuttleJobScheduleCreateRequestV2026R0RunModeField runMode,
        ShuttleJobScheduleTriggerByTimeV2026R0 trigger) {
      super();
      this.jobId = jobId;
      this.runMode = new EnumWrapper<ShuttleJobScheduleCreateRequestV2026R0RunModeField>(runMode);
      this.trigger = new ShuttleJobScheduleTriggerV2026R0(trigger);
    }

    public Builder(
        String jobId,
        ShuttleJobScheduleCreateRequestV2026R0RunModeField runMode,
        ShuttleJobScheduleTriggerByJobV2026R0 trigger) {
      super();
      this.jobId = jobId;
      this.runMode = new EnumWrapper<ShuttleJobScheduleCreateRequestV2026R0RunModeField>(runMode);
      this.trigger = new ShuttleJobScheduleTriggerV2026R0(trigger);
    }

    public Builder(
        String jobId,
        ShuttleJobScheduleCreateRequestV2026R0RunModeField runMode,
        ShuttleJobScheduleTriggerV2026R0 trigger) {
      super();
      this.jobId = jobId;
      this.runMode = new EnumWrapper<ShuttleJobScheduleCreateRequestV2026R0RunModeField>(runMode);
      this.trigger = trigger;
    }

    public Builder(
        String jobId,
        EnumWrapper<ShuttleJobScheduleCreateRequestV2026R0RunModeField> runMode,
        ShuttleJobScheduleTriggerByTimeV2026R0 trigger) {
      super();
      this.jobId = jobId;
      this.runMode = runMode;
      this.trigger = new ShuttleJobScheduleTriggerV2026R0(trigger);
    }

    public Builder(
        String jobId,
        EnumWrapper<ShuttleJobScheduleCreateRequestV2026R0RunModeField> runMode,
        ShuttleJobScheduleTriggerByJobV2026R0 trigger) {
      super();
      this.jobId = jobId;
      this.runMode = runMode;
      this.trigger = new ShuttleJobScheduleTriggerV2026R0(trigger);
    }

    public Builder(
        String jobId,
        EnumWrapper<ShuttleJobScheduleCreateRequestV2026R0RunModeField> runMode,
        ShuttleJobScheduleTriggerV2026R0 trigger) {
      super();
      this.jobId = jobId;
      this.runMode = runMode;
      this.trigger = trigger;
    }

    public Builder state(ShuttleJobScheduleCreateRequestV2026R0StateField state) {
      this.state = new EnumWrapper<ShuttleJobScheduleCreateRequestV2026R0StateField>(state);
      return this;
    }

    public Builder state(EnumWrapper<ShuttleJobScheduleCreateRequestV2026R0StateField> state) {
      this.state = state;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      this.markNullableFieldAsSet("description");
      return this;
    }

    public ShuttleJobScheduleCreateRequestV2026R0 build() {
      return new ShuttleJobScheduleCreateRequestV2026R0(this);
    }
  }
}
