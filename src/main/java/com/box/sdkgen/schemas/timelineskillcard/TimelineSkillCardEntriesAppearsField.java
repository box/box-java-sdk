package com.box.sdkgen.schemas.timelineskillcard;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class TimelineSkillCardEntriesAppearsField extends SerializableObject {

  /** The time in seconds when an entry should start appearing on a timeline. */
  protected Long start;

  /** The time in seconds when an entry should stop appearing on a timeline. */
  protected Long end;

  public TimelineSkillCardEntriesAppearsField() {
    super();
  }

  protected TimelineSkillCardEntriesAppearsField(Builder builder) {
    super();
    this.start = builder.start;
    this.end = builder.end;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Long getStart() {
    return start;
  }

  public Long getEnd() {
    return end;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TimelineSkillCardEntriesAppearsField casted = (TimelineSkillCardEntriesAppearsField) o;
    return Objects.equals(start, casted.start) && Objects.equals(end, casted.end);
  }

  @Override
  public int hashCode() {
    return Objects.hash(start, end);
  }

  @Override
  public String toString() {
    return "TimelineSkillCardEntriesAppearsField{"
        + "start='"
        + start
        + '\''
        + ", "
        + "end='"
        + end
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Long start;

    protected Long end;

    public Builder start(Long start) {
      this.start = start;
      return this;
    }

    public Builder end(Long end) {
      this.end = end;
      return this;
    }

    public TimelineSkillCardEntriesAppearsField build() {
      return new TimelineSkillCardEntriesAppearsField(this);
    }
  }
}
