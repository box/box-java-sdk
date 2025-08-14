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
import java.util.Date;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class IntegrationMappingTeams extends IntegrationMappingBase {

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

  @JsonProperty("is_overridden_by_manual_mapping")
  protected Boolean isOverriddenByManualMapping;

  @JsonProperty("partner_item")
  protected final IntegrationMappingPartnerItemTeams partnerItem;

  @JsonProperty("box_item")
  protected final FolderReference boxItem;

  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date createdAt;

  @JsonProperty("modified_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date modifiedAt;

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

  public Date getCreatedAt() {
    return createdAt;
  }

  public Date getModifiedAt() {
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

    protected Date createdAt;

    protected Date modifiedAt;

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

    public Builder createdAt(Date createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Builder modifiedAt(Date modifiedAt) {
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
      return new IntegrationMappingTeams(this);
    }
  }
}
