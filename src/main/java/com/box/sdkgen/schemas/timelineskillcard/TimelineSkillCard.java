package com.box.sdkgen.schemas.timelineskillcard;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class TimelineSkillCard extends SerializableObject {

  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date createdAt;

  @JsonDeserialize(using = TimelineSkillCardTypeField.TimelineSkillCardTypeFieldDeserializer.class)
  @JsonSerialize(using = TimelineSkillCardTypeField.TimelineSkillCardTypeFieldSerializer.class)
  protected EnumWrapper<TimelineSkillCardTypeField> type;

  @JsonDeserialize(
      using =
          TimelineSkillCardSkillCardTypeField.TimelineSkillCardSkillCardTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          TimelineSkillCardSkillCardTypeField.TimelineSkillCardSkillCardTypeFieldSerializer.class)
  @JsonProperty("skill_card_type")
  protected EnumWrapper<TimelineSkillCardSkillCardTypeField> skillCardType;

  @JsonProperty("skill_card_title")
  protected TimelineSkillCardSkillCardTitleField skillCardTitle;

  protected final TimelineSkillCardSkillField skill;

  protected final TimelineSkillCardInvocationField invocation;

  protected Long duration;

  protected final List<TimelineSkillCardEntriesField> entries;

  public TimelineSkillCard(
      @JsonProperty("skill") TimelineSkillCardSkillField skill,
      @JsonProperty("invocation") TimelineSkillCardInvocationField invocation,
      @JsonProperty("entries") List<TimelineSkillCardEntriesField> entries) {
    super();
    this.skill = skill;
    this.invocation = invocation;
    this.entries = entries;
    this.type = new EnumWrapper<TimelineSkillCardTypeField>(TimelineSkillCardTypeField.SKILL_CARD);
    this.skillCardType =
        new EnumWrapper<TimelineSkillCardSkillCardTypeField>(
            TimelineSkillCardSkillCardTypeField.TIMELINE);
  }

  protected TimelineSkillCard(Builder builder) {
    super();
    this.createdAt = builder.createdAt;
    this.type = builder.type;
    this.skillCardType = builder.skillCardType;
    this.skillCardTitle = builder.skillCardTitle;
    this.skill = builder.skill;
    this.invocation = builder.invocation;
    this.duration = builder.duration;
    this.entries = builder.entries;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public EnumWrapper<TimelineSkillCardTypeField> getType() {
    return type;
  }

  public EnumWrapper<TimelineSkillCardSkillCardTypeField> getSkillCardType() {
    return skillCardType;
  }

  public TimelineSkillCardSkillCardTitleField getSkillCardTitle() {
    return skillCardTitle;
  }

  public TimelineSkillCardSkillField getSkill() {
    return skill;
  }

  public TimelineSkillCardInvocationField getInvocation() {
    return invocation;
  }

  public Long getDuration() {
    return duration;
  }

  public List<TimelineSkillCardEntriesField> getEntries() {
    return entries;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TimelineSkillCard casted = (TimelineSkillCard) o;
    return Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(type, casted.type)
        && Objects.equals(skillCardType, casted.skillCardType)
        && Objects.equals(skillCardTitle, casted.skillCardTitle)
        && Objects.equals(skill, casted.skill)
        && Objects.equals(invocation, casted.invocation)
        && Objects.equals(duration, casted.duration)
        && Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        createdAt, type, skillCardType, skillCardTitle, skill, invocation, duration, entries);
  }

  @Override
  public String toString() {
    return "TimelineSkillCard{"
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "skillCardType='"
        + skillCardType
        + '\''
        + ", "
        + "skillCardTitle='"
        + skillCardTitle
        + '\''
        + ", "
        + "skill='"
        + skill
        + '\''
        + ", "
        + "invocation='"
        + invocation
        + '\''
        + ", "
        + "duration='"
        + duration
        + '\''
        + ", "
        + "entries='"
        + entries
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected Date createdAt;

    protected EnumWrapper<TimelineSkillCardTypeField> type;

    protected EnumWrapper<TimelineSkillCardSkillCardTypeField> skillCardType;

    protected TimelineSkillCardSkillCardTitleField skillCardTitle;

    protected final TimelineSkillCardSkillField skill;

    protected final TimelineSkillCardInvocationField invocation;

    protected Long duration;

    protected final List<TimelineSkillCardEntriesField> entries;

    public Builder(
        TimelineSkillCardSkillField skill,
        TimelineSkillCardInvocationField invocation,
        List<TimelineSkillCardEntriesField> entries) {
      super();
      this.skill = skill;
      this.invocation = invocation;
      this.entries = entries;
      this.type =
          new EnumWrapper<TimelineSkillCardTypeField>(TimelineSkillCardTypeField.SKILL_CARD);
      this.skillCardType =
          new EnumWrapper<TimelineSkillCardSkillCardTypeField>(
              TimelineSkillCardSkillCardTypeField.TIMELINE);
    }

    public Builder createdAt(Date createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder type(TimelineSkillCardTypeField type) {
      this.type = new EnumWrapper<TimelineSkillCardTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<TimelineSkillCardTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder skillCardType(TimelineSkillCardSkillCardTypeField skillCardType) {
      this.skillCardType = new EnumWrapper<TimelineSkillCardSkillCardTypeField>(skillCardType);
      return this;
    }

    public Builder skillCardType(EnumWrapper<TimelineSkillCardSkillCardTypeField> skillCardType) {
      this.skillCardType = skillCardType;
      return this;
    }

    public Builder skillCardTitle(TimelineSkillCardSkillCardTitleField skillCardTitle) {
      this.skillCardTitle = skillCardTitle;
      return this;
    }

    public Builder duration(Long duration) {
      this.duration = duration;
      return this;
    }

    public TimelineSkillCard build() {
      return new TimelineSkillCard(this);
    }
  }
}
