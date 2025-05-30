package com.box.sdkgen.managers.integrationmappings;

import com.box.sdkgen.serialization.json.EnumWrapper;

public class GetTeamsIntegrationMappingQueryParams {

  public EnumWrapper<GetTeamsIntegrationMappingQueryParamsPartnerItemTypeField> partnerItemType;

  public String partnerItemId;

  public String boxItemId;

  public EnumWrapper<GetTeamsIntegrationMappingQueryParamsBoxItemTypeField> boxItemType;

  public GetTeamsIntegrationMappingQueryParams() {}

  protected GetTeamsIntegrationMappingQueryParams(
      GetTeamsIntegrationMappingQueryParamsBuilder builder) {
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

  public static class GetTeamsIntegrationMappingQueryParamsBuilder {

    protected EnumWrapper<GetTeamsIntegrationMappingQueryParamsPartnerItemTypeField>
        partnerItemType;

    protected String partnerItemId;

    protected String boxItemId;

    protected EnumWrapper<GetTeamsIntegrationMappingQueryParamsBoxItemTypeField> boxItemType;

    public GetTeamsIntegrationMappingQueryParamsBuilder partnerItemType(
        GetTeamsIntegrationMappingQueryParamsPartnerItemTypeField partnerItemType) {
      this.partnerItemType =
          new EnumWrapper<GetTeamsIntegrationMappingQueryParamsPartnerItemTypeField>(
              partnerItemType);
      return this;
    }

    public GetTeamsIntegrationMappingQueryParamsBuilder partnerItemType(
        EnumWrapper<GetTeamsIntegrationMappingQueryParamsPartnerItemTypeField> partnerItemType) {
      this.partnerItemType = partnerItemType;
      return this;
    }

    public GetTeamsIntegrationMappingQueryParamsBuilder partnerItemId(String partnerItemId) {
      this.partnerItemId = partnerItemId;
      return this;
    }

    public GetTeamsIntegrationMappingQueryParamsBuilder boxItemId(String boxItemId) {
      this.boxItemId = boxItemId;
      return this;
    }

    public GetTeamsIntegrationMappingQueryParamsBuilder boxItemType(
        GetTeamsIntegrationMappingQueryParamsBoxItemTypeField boxItemType) {
      this.boxItemType =
          new EnumWrapper<GetTeamsIntegrationMappingQueryParamsBoxItemTypeField>(boxItemType);
      return this;
    }

    public GetTeamsIntegrationMappingQueryParamsBuilder boxItemType(
        EnumWrapper<GetTeamsIntegrationMappingQueryParamsBoxItemTypeField> boxItemType) {
      this.boxItemType = boxItemType;
      return this;
    }

    public GetTeamsIntegrationMappingQueryParams build() {
      return new GetTeamsIntegrationMappingQueryParams(this);
    }
  }
}
