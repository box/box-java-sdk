package com.box.sdkgen.schemas.integrationmappingteams;

import com.box.sdkgen.schemas.folderreference.FolderReference;
import com.box.sdkgen.schemas.integrationmappingbase.IntegrationMappingBase;
import com.box.sdkgen.schemas.integrationmappingbase.IntegrationMappingBaseTypeField;
import com.box.sdkgen.schemas.integrationmappingpartneritemteamsunion.IntegrationMappingPartnerItemTeamsUnion;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

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
  protected final IntegrationMappingPartnerItemTeamsUnion partnerItem;

  @JsonProperty("box_item")
  protected final FolderReference boxItem;

  @JsonProperty("created_at")
  protected String createdAt;

  @JsonProperty("modified_at")
  protected String modifiedAt;

  public IntegrationMappingTeams(
      @JsonProperty("id") String id,
      @JsonProperty("partner_item") IntegrationMappingPartnerItemTeamsUnion partnerItem,
      @JsonProperty("box_item") FolderReference boxItem) {
    super(id);
    this.partnerItem = partnerItem;
    this.boxItem = boxItem;
  }

  protected IntegrationMappingTeams(IntegrationMappingTeamsBuilder builder) {
    super(builder);
    this.integrationType = builder.integrationType;
    this.isOverriddenByManualMapping = builder.isOverriddenByManualMapping;
    this.partnerItem = builder.partnerItem;
    this.boxItem = builder.boxItem;
    this.createdAt = builder.createdAt;
    this.modifiedAt = builder.modifiedAt;
  }

  public EnumWrapper<IntegrationMappingTeamsIntegrationTypeField> getIntegrationType() {
    return integrationType;
  }

  public Boolean getIsOverriddenByManualMapping() {
    return isOverriddenByManualMapping;
  }

  public IntegrationMappingPartnerItemTeamsUnion getPartnerItem() {
    return partnerItem;
  }

  public FolderReference getBoxItem() {
    return boxItem;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public String getModifiedAt() {
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

  public static class IntegrationMappingTeamsBuilder extends IntegrationMappingBaseBuilder {

    protected EnumWrapper<IntegrationMappingTeamsIntegrationTypeField> integrationType;

    protected Boolean isOverriddenByManualMapping;

    protected final IntegrationMappingPartnerItemTeamsUnion partnerItem;

    protected final FolderReference boxItem;

    protected String createdAt;

    protected String modifiedAt;

    public IntegrationMappingTeamsBuilder(
        String id, IntegrationMappingPartnerItemTeamsUnion partnerItem, FolderReference boxItem) {
      super(id);
      this.partnerItem = partnerItem;
      this.boxItem = boxItem;
    }

    public IntegrationMappingTeamsBuilder integrationType(
        IntegrationMappingTeamsIntegrationTypeField integrationType) {
      this.integrationType =
          new EnumWrapper<IntegrationMappingTeamsIntegrationTypeField>(integrationType);
      return this;
    }

    public IntegrationMappingTeamsBuilder integrationType(
        EnumWrapper<IntegrationMappingTeamsIntegrationTypeField> integrationType) {
      this.integrationType = integrationType;
      return this;
    }

    public IntegrationMappingTeamsBuilder isOverriddenByManualMapping(
        Boolean isOverriddenByManualMapping) {
      this.isOverriddenByManualMapping = isOverriddenByManualMapping;
      return this;
    }

    public IntegrationMappingTeamsBuilder createdAt(String createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public IntegrationMappingTeamsBuilder modifiedAt(String modifiedAt) {
      this.modifiedAt = modifiedAt;
      return this;
    }

    @Override
    public IntegrationMappingTeamsBuilder type(IntegrationMappingBaseTypeField type) {
      this.type = new EnumWrapper<IntegrationMappingBaseTypeField>(type);
      return this;
    }

    @Override
    public IntegrationMappingTeamsBuilder type(EnumWrapper<IntegrationMappingBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public IntegrationMappingTeams build() {
      return new IntegrationMappingTeams(this);
    }
  }
}
