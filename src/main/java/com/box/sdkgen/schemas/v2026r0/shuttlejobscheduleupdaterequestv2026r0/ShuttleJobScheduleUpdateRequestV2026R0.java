package com.box.sdkgen.schemas.v2026r0.shuttlejobscheduleupdaterequestv2026r0;

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

/**
 * The request body used to update an existing shuttle job schedule. Only the fields supplied in the
 * request body are changed.
 */
@JsonFilter("nullablePropertyFilter")
public class ShuttleJobScheduleUpdateRequestV2026R0 extends SerializableObject {

  /** Whether the schedule is active. */
  @JsonDeserialize(
      using =
          ShuttleJobScheduleUpdateRequestV2026R0StateField
              .ShuttleJobScheduleUpdateRequestV2026R0StateFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShuttleJobScheduleUpdateRequestV2026R0StateField
              .ShuttleJobScheduleUpdateRequestV2026R0StateFieldSerializer.class)
  protected EnumWrapper<ShuttleJobScheduleUpdateRequestV2026R0StateField> state;

  /** The kind of run the schedule starts. `scan` is not a valid schedule run mode. */
  @JsonDeserialize(
      using =
          ShuttleJobScheduleUpdateRequestV2026R0RunModeField
              .ShuttleJobScheduleUpdateRequestV2026R0RunModeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShuttleJobScheduleUpdateRequestV2026R0RunModeField
              .ShuttleJobScheduleUpdateRequestV2026R0RunModeFieldSerializer.class)
  @JsonProperty("run_mode")
  protected EnumWrapper<ShuttleJobScheduleUpdateRequestV2026R0RunModeField> runMode;

  /** An optional human-readable description of the schedule. */
  @Nullable protected String description;

  protected ShuttleJobScheduleTriggerV2026R0 trigger;

  public ShuttleJobScheduleUpdateRequestV2026R0() {
    super();
  }

  protected ShuttleJobScheduleUpdateRequestV2026R0(Builder builder) {
    super();
    this.state = builder.state;
    this.runMode = builder.runMode;
    this.description = builder.description;
    this.trigger = builder.trigger;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<ShuttleJobScheduleUpdateRequestV2026R0StateField> getState() {
    return state;
  }

  public EnumWrapper<ShuttleJobScheduleUpdateRequestV2026R0RunModeField> getRunMode() {
    return runMode;
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
    ShuttleJobScheduleUpdateRequestV2026R0 casted = (ShuttleJobScheduleUpdateRequestV2026R0) o;
    return Objects.equals(state, casted.state)
        && Objects.equals(runMode, casted.runMode)
        && Objects.equals(description, casted.description)
        && Objects.equals(trigger, casted.trigger);
  }

  @Override
  public int hashCode() {
    return Objects.hash(state, runMode, description, trigger);
  }

  @Override
  public String toString() {
    return "ShuttleJobScheduleUpdateRequestV2026R0{"
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
        + "trigger='"
        + trigger
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<ShuttleJobScheduleUpdateRequestV2026R0StateField> state;

    protected EnumWrapper<ShuttleJobScheduleUpdateRequestV2026R0RunModeField> runMode;

    protected String description;

    protected ShuttleJobScheduleTriggerV2026R0 trigger;

    public Builder state(ShuttleJobScheduleUpdateRequestV2026R0StateField state) {
      this.state = new EnumWrapper<ShuttleJobScheduleUpdateRequestV2026R0StateField>(state);
      return this;
    }

    public Builder state(EnumWrapper<ShuttleJobScheduleUpdateRequestV2026R0StateField> state) {
      this.state = state;
      return this;
    }

    public Builder runMode(ShuttleJobScheduleUpdateRequestV2026R0RunModeField runMode) {
      this.runMode = new EnumWrapper<ShuttleJobScheduleUpdateRequestV2026R0RunModeField>(runMode);
      return this;
    }

    public Builder runMode(
        EnumWrapper<ShuttleJobScheduleUpdateRequestV2026R0RunModeField> runMode) {
      this.runMode = runMode;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      this.markNullableFieldAsSet("description");
      return this;
    }

    public Builder trigger(ShuttleJobScheduleTriggerByTimeV2026R0 trigger) {
      this.trigger = new ShuttleJobScheduleTriggerV2026R0(trigger);
      return this;
    }

    public Builder trigger(ShuttleJobScheduleTriggerByJobV2026R0 trigger) {
      this.trigger = new ShuttleJobScheduleTriggerV2026R0(trigger);
      return this;
    }

    public Builder trigger(ShuttleJobScheduleTriggerV2026R0 trigger) {
      this.trigger = trigger;
      return this;
    }

    public ShuttleJobScheduleUpdateRequestV2026R0 build() {
      return new ShuttleJobScheduleUpdateRequestV2026R0(this);
    }
  }
}
