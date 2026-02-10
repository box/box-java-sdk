package com.box.sdkgen.schemas.statusskillcard;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

/** A Box Skill metadata card that puts a status message in the metadata sidebar. */
@JsonFilter("nullablePropertyFilter")
public class StatusSkillCard extends SerializableObject {

  /** The optional date and time this card was created at. */
  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime createdAt;

  /** The value will always be `skill_card`. */
  @JsonDeserialize(using = StatusSkillCardTypeField.StatusSkillCardTypeFieldDeserializer.class)
  @JsonSerialize(using = StatusSkillCardTypeField.StatusSkillCardTypeFieldSerializer.class)
  protected EnumWrapper<StatusSkillCardTypeField> type;

  /** The value will always be `status`. */
  @JsonDeserialize(
      using = StatusSkillCardSkillCardTypeField.StatusSkillCardSkillCardTypeFieldDeserializer.class)
  @JsonSerialize(
      using = StatusSkillCardSkillCardTypeField.StatusSkillCardSkillCardTypeFieldSerializer.class)
  @JsonProperty("skill_card_type")
  protected EnumWrapper<StatusSkillCardSkillCardTypeField> skillCardType;

  /** The title of the card. */
  @JsonProperty("skill_card_title")
  protected StatusSkillCardSkillCardTitleField skillCardTitle;

  /**
   * Sets the status of the skill. This can be used to show a message to the user while the Skill is
   * processing the data, or if it was not able to process the file.
   */
  protected final StatusSkillCardStatusField status;

  /** The service that applied this metadata. */
  protected final StatusSkillCardSkillField skill;

  /**
   * The invocation of this service, used to track which instance of a service applied the metadata.
   */
  protected final StatusSkillCardInvocationField invocation;

  public StatusSkillCard(
      @JsonProperty("status") StatusSkillCardStatusField status,
      @JsonProperty("skill") StatusSkillCardSkillField skill,
      @JsonProperty("invocation") StatusSkillCardInvocationField invocation) {
    super();
    this.status = status;
    this.skill = skill;
    this.invocation = invocation;
    this.type = new EnumWrapper<StatusSkillCardTypeField>(StatusSkillCardTypeField.SKILL_CARD);
    this.skillCardType =
        new EnumWrapper<StatusSkillCardSkillCardTypeField>(
            StatusSkillCardSkillCardTypeField.STATUS);
  }

  protected StatusSkillCard(Builder builder) {
    super();
    this.createdAt = builder.createdAt;
    this.type = builder.type;
    this.skillCardType = builder.skillCardType;
    this.skillCardTitle = builder.skillCardTitle;
    this.status = builder.status;
    this.skill = builder.skill;
    this.invocation = builder.invocation;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public EnumWrapper<StatusSkillCardTypeField> getType() {
    return type;
  }

  public EnumWrapper<StatusSkillCardSkillCardTypeField> getSkillCardType() {
    return skillCardType;
  }

  public StatusSkillCardSkillCardTitleField getSkillCardTitle() {
    return skillCardTitle;
  }

  public StatusSkillCardStatusField getStatus() {
    return status;
  }

  public StatusSkillCardSkillField getSkill() {
    return skill;
  }

  public StatusSkillCardInvocationField getInvocation() {
    return invocation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StatusSkillCard casted = (StatusSkillCard) o;
    return Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(type, casted.type)
        && Objects.equals(skillCardType, casted.skillCardType)
        && Objects.equals(skillCardTitle, casted.skillCardTitle)
        && Objects.equals(status, casted.status)
        && Objects.equals(skill, casted.skill)
        && Objects.equals(invocation, casted.invocation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(createdAt, type, skillCardType, skillCardTitle, status, skill, invocation);
  }

  @Override
  public String toString() {
    return "StatusSkillCard{"
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
        + "status='"
        + status
        + '\''
        + ", "
        + "skill='"
        + skill
        + '\''
        + ", "
        + "invocation='"
        + invocation
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected OffsetDateTime createdAt;

    protected EnumWrapper<StatusSkillCardTypeField> type;

    protected EnumWrapper<StatusSkillCardSkillCardTypeField> skillCardType;

    protected StatusSkillCardSkillCardTitleField skillCardTitle;

    protected final StatusSkillCardStatusField status;

    protected final StatusSkillCardSkillField skill;

    protected final StatusSkillCardInvocationField invocation;

    public Builder(
        StatusSkillCardStatusField status,
        StatusSkillCardSkillField skill,
        StatusSkillCardInvocationField invocation) {
      super();
      this.status = status;
      this.skill = skill;
      this.invocation = invocation;
    }

    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder type(StatusSkillCardTypeField type) {
      this.type = new EnumWrapper<StatusSkillCardTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<StatusSkillCardTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder skillCardType(StatusSkillCardSkillCardTypeField skillCardType) {
      this.skillCardType = new EnumWrapper<StatusSkillCardSkillCardTypeField>(skillCardType);
      return this;
    }

    public Builder skillCardType(EnumWrapper<StatusSkillCardSkillCardTypeField> skillCardType) {
      this.skillCardType = skillCardType;
      return this;
    }

    public Builder skillCardTitle(StatusSkillCardSkillCardTitleField skillCardTitle) {
      this.skillCardTitle = skillCardTitle;
      return this;
    }

    public StatusSkillCard build() {
      if (this.type == null) {
        this.type = new EnumWrapper<StatusSkillCardTypeField>(StatusSkillCardTypeField.SKILL_CARD);
      }
      if (this.skillCardType == null) {
        this.skillCardType =
            new EnumWrapper<StatusSkillCardSkillCardTypeField>(
                StatusSkillCardSkillCardTypeField.STATUS);
      }
      return new StatusSkillCard(this);
    }
  }
}
