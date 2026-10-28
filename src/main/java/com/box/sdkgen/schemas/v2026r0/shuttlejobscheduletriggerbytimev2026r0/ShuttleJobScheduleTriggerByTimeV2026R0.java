package com.box.sdkgen.schemas.v2026r0.shuttlejobscheduletriggerbytimev2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** A time-based trigger that starts the schedule at a wall-clock time and optionally repeats. */
@JsonFilter("nullablePropertyFilter")
public class ShuttleJobScheduleTriggerByTimeV2026R0 extends SerializableObject {

  /** The value will always be `by_time`. */
  @JsonDeserialize(
      using =
          ShuttleJobScheduleTriggerByTimeV2026R0TypeField
              .ShuttleJobScheduleTriggerByTimeV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShuttleJobScheduleTriggerByTimeV2026R0TypeField
              .ShuttleJobScheduleTriggerByTimeV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<ShuttleJobScheduleTriggerByTimeV2026R0TypeField> type;

  /**
   * The local wall-clock time when the schedule should first start a run, without an offset or `Z`
   * suffix. Combined with `time_zone` to determine the instant, including DST.
   */
  @JsonProperty("start_at")
  protected final String startAt;

  /**
   * The IANA time zone that gives `start_at` its calendar meaning (for example
   * `America/Los_Angeles`). Recurrence and DST transitions are evaluated in this zone.
   */
  @JsonProperty("time_zone")
  protected final String timeZone;

  /** How often the schedule repeats after `start_at`. */
  @JsonDeserialize(
      using =
          ShuttleJobScheduleTriggerByTimeV2026R0RepeatField
              .ShuttleJobScheduleTriggerByTimeV2026R0RepeatFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShuttleJobScheduleTriggerByTimeV2026R0RepeatField
              .ShuttleJobScheduleTriggerByTimeV2026R0RepeatFieldSerializer.class)
  protected final EnumWrapper<ShuttleJobScheduleTriggerByTimeV2026R0RepeatField> repeat;

  public ShuttleJobScheduleTriggerByTimeV2026R0(
      String startAt, String timeZone, ShuttleJobScheduleTriggerByTimeV2026R0RepeatField repeat) {
    super();
    this.startAt = startAt;
    this.timeZone = timeZone;
    this.repeat = new EnumWrapper<ShuttleJobScheduleTriggerByTimeV2026R0RepeatField>(repeat);
    this.type =
        new EnumWrapper<ShuttleJobScheduleTriggerByTimeV2026R0TypeField>(
            ShuttleJobScheduleTriggerByTimeV2026R0TypeField.BY_TIME);
  }

  public ShuttleJobScheduleTriggerByTimeV2026R0(
      @JsonProperty("start_at") String startAt,
      @JsonProperty("time_zone") String timeZone,
      @JsonProperty("repeat")
          EnumWrapper<ShuttleJobScheduleTriggerByTimeV2026R0RepeatField> repeat) {
    super();
    this.startAt = startAt;
    this.timeZone = timeZone;
    this.repeat = repeat;
    this.type =
        new EnumWrapper<ShuttleJobScheduleTriggerByTimeV2026R0TypeField>(
            ShuttleJobScheduleTriggerByTimeV2026R0TypeField.BY_TIME);
  }

  protected ShuttleJobScheduleTriggerByTimeV2026R0(Builder builder) {
    super();
    this.type = builder.type;
    this.startAt = builder.startAt;
    this.timeZone = builder.timeZone;
    this.repeat = builder.repeat;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<ShuttleJobScheduleTriggerByTimeV2026R0TypeField> getType() {
    return type;
  }

  public String getStartAt() {
    return startAt;
  }

  public String getTimeZone() {
    return timeZone;
  }

  public EnumWrapper<ShuttleJobScheduleTriggerByTimeV2026R0RepeatField> getRepeat() {
    return repeat;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttleJobScheduleTriggerByTimeV2026R0 casted = (ShuttleJobScheduleTriggerByTimeV2026R0) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(startAt, casted.startAt)
        && Objects.equals(timeZone, casted.timeZone)
        && Objects.equals(repeat, casted.repeat);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, startAt, timeZone, repeat);
  }

  @Override
  public String toString() {
    return "ShuttleJobScheduleTriggerByTimeV2026R0{"
        + "type='"
        + type
        + '\''
        + ", "
        + "startAt='"
        + startAt
        + '\''
        + ", "
        + "timeZone='"
        + timeZone
        + '\''
        + ", "
        + "repeat='"
        + repeat
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<ShuttleJobScheduleTriggerByTimeV2026R0TypeField> type;

    protected final String startAt;

    protected final String timeZone;

    protected final EnumWrapper<ShuttleJobScheduleTriggerByTimeV2026R0RepeatField> repeat;

    public Builder(
        String startAt, String timeZone, ShuttleJobScheduleTriggerByTimeV2026R0RepeatField repeat) {
      super();
      this.startAt = startAt;
      this.timeZone = timeZone;
      this.repeat = new EnumWrapper<ShuttleJobScheduleTriggerByTimeV2026R0RepeatField>(repeat);
    }

    public Builder(
        String startAt,
        String timeZone,
        EnumWrapper<ShuttleJobScheduleTriggerByTimeV2026R0RepeatField> repeat) {
      super();
      this.startAt = startAt;
      this.timeZone = timeZone;
      this.repeat = repeat;
    }

    public Builder type(ShuttleJobScheduleTriggerByTimeV2026R0TypeField type) {
      this.type = new EnumWrapper<ShuttleJobScheduleTriggerByTimeV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShuttleJobScheduleTriggerByTimeV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public ShuttleJobScheduleTriggerByTimeV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<ShuttleJobScheduleTriggerByTimeV2026R0TypeField>(
                ShuttleJobScheduleTriggerByTimeV2026R0TypeField.BY_TIME);
      }
      return new ShuttleJobScheduleTriggerByTimeV2026R0(this);
    }
  }
}
