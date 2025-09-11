package com.box.sdkgen.schemas.keywordskillcard;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class KeywordSkillCard extends SerializableObject {

  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime createdAt;

  @JsonDeserialize(using = KeywordSkillCardTypeField.KeywordSkillCardTypeFieldDeserializer.class)
  @JsonSerialize(using = KeywordSkillCardTypeField.KeywordSkillCardTypeFieldSerializer.class)
  protected EnumWrapper<KeywordSkillCardTypeField> type;

  @JsonDeserialize(
      using =
          KeywordSkillCardSkillCardTypeField.KeywordSkillCardSkillCardTypeFieldDeserializer.class)
  @JsonSerialize(
      using = KeywordSkillCardSkillCardTypeField.KeywordSkillCardSkillCardTypeFieldSerializer.class)
  @JsonProperty("skill_card_type")
  protected EnumWrapper<KeywordSkillCardSkillCardTypeField> skillCardType;

  @JsonProperty("skill_card_title")
  protected KeywordSkillCardSkillCardTitleField skillCardTitle;

  protected final KeywordSkillCardSkillField skill;

  protected final KeywordSkillCardInvocationField invocation;

  protected final List<KeywordSkillCardEntriesField> entries;

  public KeywordSkillCard(
      @JsonProperty("skill") KeywordSkillCardSkillField skill,
      @JsonProperty("invocation") KeywordSkillCardInvocationField invocation,
      @JsonProperty("entries") List<KeywordSkillCardEntriesField> entries) {
    super();
    this.skill = skill;
    this.invocation = invocation;
    this.entries = entries;
    this.type = new EnumWrapper<KeywordSkillCardTypeField>(KeywordSkillCardTypeField.SKILL_CARD);
    this.skillCardType =
        new EnumWrapper<KeywordSkillCardSkillCardTypeField>(
            KeywordSkillCardSkillCardTypeField.KEYWORD);
  }

  protected KeywordSkillCard(Builder builder) {
    super();
    this.createdAt = builder.createdAt;
    this.type = builder.type;
    this.skillCardType = builder.skillCardType;
    this.skillCardTitle = builder.skillCardTitle;
    this.skill = builder.skill;
    this.invocation = builder.invocation;
    this.entries = builder.entries;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public EnumWrapper<KeywordSkillCardTypeField> getType() {
    return type;
  }

  public EnumWrapper<KeywordSkillCardSkillCardTypeField> getSkillCardType() {
    return skillCardType;
  }

  public KeywordSkillCardSkillCardTitleField getSkillCardTitle() {
    return skillCardTitle;
  }

  public KeywordSkillCardSkillField getSkill() {
    return skill;
  }

  public KeywordSkillCardInvocationField getInvocation() {
    return invocation;
  }

  public List<KeywordSkillCardEntriesField> getEntries() {
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
    KeywordSkillCard casted = (KeywordSkillCard) o;
    return Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(type, casted.type)
        && Objects.equals(skillCardType, casted.skillCardType)
        && Objects.equals(skillCardTitle, casted.skillCardTitle)
        && Objects.equals(skill, casted.skill)
        && Objects.equals(invocation, casted.invocation)
        && Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(createdAt, type, skillCardType, skillCardTitle, skill, invocation, entries);
  }

  @Override
  public String toString() {
    return "KeywordSkillCard{"
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
        + "entries='"
        + entries
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected OffsetDateTime createdAt;

    protected EnumWrapper<KeywordSkillCardTypeField> type;

    protected EnumWrapper<KeywordSkillCardSkillCardTypeField> skillCardType;

    protected KeywordSkillCardSkillCardTitleField skillCardTitle;

    protected final KeywordSkillCardSkillField skill;

    protected final KeywordSkillCardInvocationField invocation;

    protected final List<KeywordSkillCardEntriesField> entries;

    public Builder(
        KeywordSkillCardSkillField skill,
        KeywordSkillCardInvocationField invocation,
        List<KeywordSkillCardEntriesField> entries) {
      super();
      this.skill = skill;
      this.invocation = invocation;
      this.entries = entries;
      this.type = new EnumWrapper<KeywordSkillCardTypeField>(KeywordSkillCardTypeField.SKILL_CARD);
      this.skillCardType =
          new EnumWrapper<KeywordSkillCardSkillCardTypeField>(
              KeywordSkillCardSkillCardTypeField.KEYWORD);
    }

    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder type(KeywordSkillCardTypeField type) {
      this.type = new EnumWrapper<KeywordSkillCardTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<KeywordSkillCardTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder skillCardType(KeywordSkillCardSkillCardTypeField skillCardType) {
      this.skillCardType = new EnumWrapper<KeywordSkillCardSkillCardTypeField>(skillCardType);
      return this;
    }

    public Builder skillCardType(EnumWrapper<KeywordSkillCardSkillCardTypeField> skillCardType) {
      this.skillCardType = skillCardType;
      return this;
    }

    public Builder skillCardTitle(KeywordSkillCardSkillCardTitleField skillCardTitle) {
      this.skillCardTitle = skillCardTitle;
      return this;
    }

    public KeywordSkillCard build() {
      return new KeywordSkillCard(this);
    }
  }
}
