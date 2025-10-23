package com.box.sdkgen.schemas.v2025r0.shieldruleitemv2025r0;

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

/** A Shield rule item. */
@JsonFilter("nullablePropertyFilter")
public class ShieldRuleItemV2025R0 extends SerializableObject {

  /** The identifier of the shield rule. */
  protected String id;

  /** The value will always be `shield_rule`. */
  @JsonDeserialize(
      using = ShieldRuleItemV2025R0TypeField.ShieldRuleItemV2025R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using = ShieldRuleItemV2025R0TypeField.ShieldRuleItemV2025R0TypeFieldSerializer.class)
  protected EnumWrapper<ShieldRuleItemV2025R0TypeField> type;

  /** The category of the shield rule. */
  @JsonProperty("rule_category")
  protected String ruleCategory;

  /** The name of the shield rule. */
  protected String name;

  /** The description of the shield rule. */
  protected String description;

  /** The priority level of the shield rule. */
  @JsonDeserialize(
      using =
          ShieldRuleItemV2025R0PriorityField.ShieldRuleItemV2025R0PriorityFieldDeserializer.class)
  @JsonSerialize(
      using = ShieldRuleItemV2025R0PriorityField.ShieldRuleItemV2025R0PriorityFieldSerializer.class)
  protected EnumWrapper<ShieldRuleItemV2025R0PriorityField> priority;

  /** The date and time when the shield rule was created. */
  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime createdAt;

  /** The date and time when the shield rule was last modified. */
  @JsonProperty("modified_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime modifiedAt;

  public ShieldRuleItemV2025R0() {
    super();
  }

  protected ShieldRuleItemV2025R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.ruleCategory = builder.ruleCategory;
    this.name = builder.name;
    this.description = builder.description;
    this.priority = builder.priority;
    this.createdAt = builder.createdAt;
    this.modifiedAt = builder.modifiedAt;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<ShieldRuleItemV2025R0TypeField> getType() {
    return type;
  }

  public String getRuleCategory() {
    return ruleCategory;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public EnumWrapper<ShieldRuleItemV2025R0PriorityField> getPriority() {
    return priority;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public OffsetDateTime getModifiedAt() {
    return modifiedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShieldRuleItemV2025R0 casted = (ShieldRuleItemV2025R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(ruleCategory, casted.ruleCategory)
        && Objects.equals(name, casted.name)
        && Objects.equals(description, casted.description)
        && Objects.equals(priority, casted.priority)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(modifiedAt, casted.modifiedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, ruleCategory, name, description, priority, createdAt, modifiedAt);
  }

  @Override
  public String toString() {
    return "ShieldRuleItemV2025R0{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "ruleCategory='"
        + ruleCategory
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + ", "
        + "description='"
        + description
        + '\''
        + ", "
        + "priority='"
        + priority
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "modifiedAt='"
        + modifiedAt
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<ShieldRuleItemV2025R0TypeField> type;

    protected String ruleCategory;

    protected String name;

    protected String description;

    protected EnumWrapper<ShieldRuleItemV2025R0PriorityField> priority;

    protected OffsetDateTime createdAt;

    protected OffsetDateTime modifiedAt;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(ShieldRuleItemV2025R0TypeField type) {
      this.type = new EnumWrapper<ShieldRuleItemV2025R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShieldRuleItemV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public Builder ruleCategory(String ruleCategory) {
      this.ruleCategory = ruleCategory;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder priority(ShieldRuleItemV2025R0PriorityField priority) {
      this.priority = new EnumWrapper<ShieldRuleItemV2025R0PriorityField>(priority);
      return this;
    }

    public Builder priority(EnumWrapper<ShieldRuleItemV2025R0PriorityField> priority) {
      this.priority = priority;
      return this;
    }

    public Builder createdAt(OffsetDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder modifiedAt(OffsetDateTime modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    public ShieldRuleItemV2025R0 build() {
      return new ShieldRuleItemV2025R0(this);
    }
  }
}
