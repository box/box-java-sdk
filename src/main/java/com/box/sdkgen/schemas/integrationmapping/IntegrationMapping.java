package com.box.sdkgen.schemas.integrationmapping;

import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.integrationmappingbase.IntegrationMappingBase;
import com.box.sdkgen.schemas.integrationmappingbase.IntegrationMappingBaseTypeField;
import com.box.sdkgen.schemas.integrationmappingpartneritemslack.IntegrationMappingPartnerItemSlack;
import com.box.sdkgen.schemas.integrationmappingslackoptions.IntegrationMappingSlackOptions;
import com.box.sdkgen.schemas.userintegrationmappings.UserIntegrationMappings;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class IntegrationMapping extends IntegrationMappingBase {

  @JsonDeserialize(
      using =
          IntegrationMappingIntegrationTypeField.IntegrationMappingIntegrationTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          IntegrationMappingIntegrationTypeField.IntegrationMappingIntegrationTypeFieldSerializer
              .class)
  @JsonProperty("integration_type")
  protected EnumWrapper<IntegrationMappingIntegrationTypeField> integrationType;

  @JsonProperty("is_manually_created")
  protected Boolean isManuallyCreated;

  protected IntegrationMappingSlackOptions options;

  @JsonProperty("created_by")
  protected UserIntegrationMappings createdBy;

  @JsonProperty("modified_by")
  protected UserIntegrationMappings modifiedBy;

  @JsonProperty("partner_item")
  protected final IntegrationMappingPartnerItemSlack partnerItem;

  @JsonProperty("box_item")
  protected final FolderMini boxItem;

  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date createdAt;

  @JsonProperty("modified_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected Date modifiedAt;

  public IntegrationMapping(
      @JsonProperty("id") String id,
      @JsonProperty("partner_item") IntegrationMappingPartnerItemSlack partnerItem,
      @JsonProperty("box_item") FolderMini boxItem) {
    super(id);
    this.partnerItem = partnerItem;
    this.boxItem = boxItem;
  }

  protected IntegrationMapping(Builder builder) {
    super(builder);
    this.integrationType = builder.integrationType;
    this.isManuallyCreated = builder.isManuallyCreated;
    this.options = builder.options;
    this.createdBy = builder.createdBy;
    this.modifiedBy = builder.modifiedBy;
    this.partnerItem = builder.partnerItem;
    this.boxItem = builder.boxItem;
    this.createdAt = builder.createdAt;
    this.modifiedAt = builder.modifiedAt;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<IntegrationMappingIntegrationTypeField> getIntegrationType() {
    return integrationType;
  }

  public Boolean getIsManuallyCreated() {
    return isManuallyCreated;
  }

  public IntegrationMappingSlackOptions getOptions() {
    return options;
  }

  public UserIntegrationMappings getCreatedBy() {
    return createdBy;
  }

  public UserIntegrationMappings getModifiedBy() {
    return modifiedBy;
  }

  public IntegrationMappingPartnerItemSlack getPartnerItem() {
    return partnerItem;
  }

  public FolderMini getBoxItem() {
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
    IntegrationMapping casted = (IntegrationMapping) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(integrationType, casted.integrationType)
        && Objects.equals(isManuallyCreated, casted.isManuallyCreated)
        && Objects.equals(options, casted.options)
        && Objects.equals(createdBy, casted.createdBy)
        && Objects.equals(modifiedBy, casted.modifiedBy)
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
        isManuallyCreated,
        options,
        createdBy,
        modifiedBy,
        partnerItem,
        boxItem,
        createdAt,
        modifiedAt);
  }

  @Override
  public String toString() {
    return "IntegrationMapping{"
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
        + "isManuallyCreated='"
        + isManuallyCreated
        + '\''
        + ", "
        + "options='"
        + options
        + '\''
        + ", "
        + "createdBy='"
        + createdBy
        + '\''
        + ", "
        + "modifiedBy='"
        + modifiedBy
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

    protected EnumWrapper<IntegrationMappingIntegrationTypeField> integrationType;

    protected Boolean isManuallyCreated;

    protected IntegrationMappingSlackOptions options;

    protected UserIntegrationMappings createdBy;

    protected UserIntegrationMappings modifiedBy;

    protected final IntegrationMappingPartnerItemSlack partnerItem;

    protected final FolderMini boxItem;

    protected Date createdAt;

    protected Date modifiedAt;

    public Builder(String id, IntegrationMappingPartnerItemSlack partnerItem, FolderMini boxItem) {
      super(id);
      this.partnerItem = partnerItem;
      this.boxItem = boxItem;
    }

    public Builder integrationType(IntegrationMappingIntegrationTypeField integrationType) {
      this.integrationType =
          new EnumWrapper<IntegrationMappingIntegrationTypeField>(integrationType);
      return this;
    }

    public Builder integrationType(
        EnumWrapper<IntegrationMappingIntegrationTypeField> integrationType) {
      this.integrationType = integrationType;
      return this;
    }

    public Builder isManuallyCreated(Boolean isManuallyCreated) {
      this.isManuallyCreated = isManuallyCreated;
      return this;
    }

    public Builder options(IntegrationMappingSlackOptions options) {
      this.options = options;
      return this;
    }

    public Builder createdBy(UserIntegrationMappings createdBy) {
      this.createdBy = createdBy;
      return this;
    }

    public Builder modifiedBy(UserIntegrationMappings modifiedBy) {
      this.modifiedBy = modifiedBy;
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

    public IntegrationMapping build() {
      return new IntegrationMapping(this);
    }
  }
}
