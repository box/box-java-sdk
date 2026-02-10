package com.box.sdkgen.schemas.integrationmappingteams;

import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.folderreference.FolderReference;
import com.box.sdkgen.schemas.integrationmappingbase.IntegrationMappingBase;
import com.box.sdkgen.schemas.integrationmappingbase.IntegrationMappingBaseTypeField;
import com.box.sdkgen.schemas.integrationmappingpartneritemteams.IntegrationMappingPartnerItemTeams;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

/** A Microsoft Teams specific representation of an integration mapping object. */
@JsonFilter("nullablePropertyFilter")
public class IntegrationMappingTeams extends IntegrationMappingBase {

  /**
   * Identifies the Box partner app, with which the mapping is associated. Supports Slack and Teams.
   * (part of the composite key together with `id`).
   */
  @JsonDeserialize(
      using =
          IntegrationMappingTeamsIntegrationTypeField
              .IntegrationMappingTeamsIntegrationTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          IntegrationMappingTeamsIntegrationTypeField
              .IntegrationMappingTeamsIntegrationTypeFieldSerializer.class)
  @JsonProperty("integration_type")
  protected EnumWrapper<IntegrationMappingTeamsIntegrationTypeField> integrationType;

  /**
   * Identifies whether the mapping has been manually set by the team owner from UI for channels (as
   * opposed to being automatically created).
   */
  @JsonProperty("is_overridden_by_manual_mapping")
  protected Boolean isOverriddenByManualMapping;

  /** Mapped item object for Teams. */
  @JsonProperty("partner_item")
  protected final IntegrationMappingPartnerItemTeams partnerItem;

  @JsonProperty("box_item")
  protected final FolderReference boxItem;

  /** When the integration mapping object was created. */
  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime createdAt;

  /** When the integration mapping object was last modified. */
  @JsonProperty("modified_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime modifiedAt;

  public IntegrationMappingTeams(
      @JsonProperty("id") String id,
      @JsonProperty("partner_item") IntegrationMappingPartnerItemTeams partnerItem,
      @JsonProperty("box_item") FolderReference boxItem) {
    super(id);
    this.partnerItem = partnerItem;
    this.boxItem = boxItem;
  }

  protected IntegrationMappingTeams(Builder builder) {
    super(builder);
    this.integrationType = builder.integrationType;
    this.isOverriddenByManualMapping = builder.isOverriddenByManualMapping;
    this.partnerItem = builder.partnerItem;
    this.boxItem = builder.boxItem;
    this.createdAt = builder.createdAt;
    this.modifiedAt = builder.modifiedAt;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<IntegrationMappingTeamsIntegrationTypeField> getIntegrationType() {
    return integrationType;
  }

  public Boolean getIsOverriddenByManualMapping() {
    return isOverriddenByManualMapping;
  }

  public IntegrationMappingPartnerItemTeams getPartnerItem() {
    return partnerItem;
  }

  public FolderReference getBoxItem() {
    return boxItem;
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
    IntegrationMappingTeams casted = (IntegrationMappingTeams) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(integrationType, casted.integrationType)
        && Objects.equals(isOverriddenByManualMapping, casted.isOverriddenByManualMapping)
        && Objects.equals(partnerItem, casted.partnerItem)
        && Objects.equals(boxItem, casted.boxItem)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(modifiedAt, casted.modifiedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        type,
        integrationType,
        isOverriddenByManualMapping,
        partnerItem,
        boxItem,
        createdAt,
        modifiedAt);
  }

  @Override
  public String toString() {
    return "IntegrationMappingTeams{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "integrationType='"
        + integrationType
        + '\''
        + ", "
        + "isOverriddenByManualMapping='"
        + isOverriddenByManualMapping
        + '\''
        + ", "
        + "partnerItem='"
        + partnerItem
        + '\''
        + ", "
        + "boxItem='"
        + boxItem
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

  public static class Builder extends IntegrationMappingBase.Builder {

    protected EnumWrapper<IntegrationMappingTeamsIntegrationTypeField> integrationType;

    protected Boolean isOverriddenByManualMapping;

    protected final IntegrationMappingPartnerItemTeams partnerItem;

    protected final FolderReference boxItem;

    protected OffsetDateTime createdAt;

    protected OffsetDateTime modifiedAt;

    public Builder(
        String id, IntegrationMappingPartnerItemTeams partnerItem, FolderReference boxItem) {
      super(id);
      this.partnerItem = partnerItem;
      this.boxItem = boxItem;
    }

    public Builder integrationType(IntegrationMappingTeamsIntegrationTypeField integrationType) {
      this.integrationType =
          new EnumWrapper<IntegrationMappingTeamsIntegrationTypeField>(integrationType);
      return this;
    }

    public Builder integrationType(
        EnumWrapper<IntegrationMappingTeamsIntegrationTypeField> integrationType) {
      this.integrationType = integrationType;
      return this;
    }

    public Builder isOverriddenByManualMapping(Boolean isOverriddenByManualMapping) {
      this.isOverriddenByManualMapping = isOverriddenByManualMapping;
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

    @Override
    public Builder type(IntegrationMappingBaseTypeField type) {
      this.type = new EnumWrapper<IntegrationMappingBaseTypeField>(type);
      return this;
    }

    @Override
    public Builder type(EnumWrapper<IntegrationMappingBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public IntegrationMappingTeams build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<IntegrationMappingBaseTypeField>(
                IntegrationMappingBaseTypeField.INTEGRATION_MAPPING);
      }
      return new IntegrationMappingTeams(this);
    }
  }
}
