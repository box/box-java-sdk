package com.box.sdkgen.schemas.timelineskillcard;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class TimelineSkillCardInvocationField extends SerializableObject {

  @JsonDeserialize(
      using =
          TimelineSkillCardInvocationTypeField.TimelineSkillCardInvocationTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          TimelineSkillCardInvocationTypeField.TimelineSkillCardInvocationTypeFieldSerializer.class)
  protected EnumWrapper<TimelineSkillCardInvocationTypeField> type;

  protected final String id;

  public TimelineSkillCardInvocationField(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<TimelineSkillCardInvocationTypeField>(
            TimelineSkillCardInvocationTypeField.SKILL_INVOCATION);
  }

  protected TimelineSkillCardInvocationField(TimelineSkillCardInvocationFieldBuilder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
  }

  public EnumWrapper<TimelineSkillCardInvocationTypeField> getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TimelineSkillCardInvocationField casted = (TimelineSkillCardInvocationField) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "TimelineSkillCardInvocationField{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + "}";
  }

  public static class TimelineSkillCardInvocationFieldBuilder {

    protected EnumWrapper<TimelineSkillCardInvocationTypeField> type;

    protected final String id;

    public TimelineSkillCardInvocationFieldBuilder(String id) {
      this.id = id;
      this.type =
          new EnumWrapper<TimelineSkillCardInvocationTypeField>(
              TimelineSkillCardInvocationTypeField.SKILL_INVOCATION);
    }

    public TimelineSkillCardInvocationFieldBuilder type(TimelineSkillCardInvocationTypeField type) {
      this.type = new EnumWrapper<TimelineSkillCardInvocationTypeField>(type);
      return this;
    }

    public TimelineSkillCardInvocationFieldBuilder type(
        EnumWrapper<TimelineSkillCardInvocationTypeField> type) {
      this.type = type;
      return this;
    }

    public TimelineSkillCardInvocationField build() {
      return new TimelineSkillCardInvocationField(this);
    }
  }
}
