package com.box.sdkgen.managers.integrationmappings;

import com.box.sdkgen.serialization.json.EnumWrapper;

public class GetTeamsIntegrationMappingQueryParams {

  /** Mapped item type, for which the mapping should be returned. */
  public EnumWrapper<GetTeamsIntegrationMappingQueryParamsPartnerItemTypeField> partnerItemType;

  /** ID of the mapped item, for which the mapping should be returned. */
  public String partnerItemId;

  /** Box item ID, for which the mappings should be returned. */
  public String boxItemId;

  /** Box item type, for which the mappings should be returned. */
  public EnumWrapper<GetTeamsIntegrationMappingQueryParamsBoxItemTypeField> boxItemType;

  public GetTeamsIntegrationMappingQueryParams() {}

  protected GetTeamsIntegrationMappingQueryParams(Builder builder) {
    this.partnerItemType = builder.partnerItemType;
    this.partnerItemId = builder.partnerItemId;
    this.boxItemId = builder.boxItemId;
    this.boxItemType = builder.boxItemType;
  }

  public EnumWrapper<GetTeamsIntegrationMappingQueryParamsPartnerItemTypeField>
      getPartnerItemType() {
    return partnerItemType;
  }

  public String getPartnerItemId() {
    return partnerItemId;
  }

  public String getBoxItemId() {
    return boxItemId;
  }

  public EnumWrapper<GetTeamsIntegrationMappingQueryParamsBoxItemTypeField> getBoxItemType() {
    return boxItemType;
  }

  public static class Builder {

    protected EnumWrapper<GetTeamsIntegrationMappingQueryParamsPartnerItemTypeField>
        partnerItemType;

    protected String partnerItemId;

    protected String boxItemId;

    protected EnumWrapper<GetTeamsIntegrationMappingQueryParamsBoxItemTypeField> boxItemType;

    public Builder partnerItemType(
        GetTeamsIntegrationMappingQueryParamsPartnerItemTypeField partnerItemType) {
      this.partnerItemType =
          new EnumWrapper<GetTeamsIntegrationMappingQueryParamsPartnerItemTypeField>(
              partnerItemType);
      return this;
    }

    public Builder partnerItemType(
        EnumWrapper<GetTeamsIntegrationMappingQueryParamsPartnerItemTypeField> partnerItemType) {
      this.partnerItemType = partnerItemType;
      return this;
    }

    public Builder partnerItemId(String partnerItemId) {
      this.partnerItemId = partnerItemId;
      return this;
    }

    public Builder boxItemId(String boxItemId) {
      this.boxItemId = boxItemId;
      return this;
    }

    public Builder boxItemType(GetTeamsIntegrationMappingQueryParamsBoxItemTypeField boxItemType) {
      this.boxItemType =
          new EnumWrapper<GetTeamsIntegrationMappingQueryParamsBoxItemTypeField>(boxItemType);
      return this;
    }

    public Builder boxItemType(
        EnumWrapper<GetTeamsIntegrationMappingQueryParamsBoxItemTypeField> boxItemType) {
      this.boxItemType = boxItemType;
      return this;
    }

    public GetTeamsIntegrationMappingQueryParams build() {
      return new GetTeamsIntegrationMappingQueryParams(this);
    }
  }
}
