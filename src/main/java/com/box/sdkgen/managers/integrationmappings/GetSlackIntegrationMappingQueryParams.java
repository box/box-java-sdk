package com.box.sdkgen.managers.integrationmappings;

import com.box.sdkgen.serialization.json.EnumWrapper;

public class GetSlackIntegrationMappingQueryParams {

  /**
   * Defines the position marker at which to begin returning results. This is used when paginating
   * using marker-based pagination.
   *
   * <p>This requires `usemarker` to be set to `true`.
   */
  public String marker;

  /** The maximum number of items to return per page. */
  public Long limit;

  /** Mapped item type, for which the mapping should be returned. */
  public EnumWrapper<GetSlackIntegrationMappingQueryParamsPartnerItemTypeField> partnerItemType;

  /** ID of the mapped item, for which the mapping should be returned. */
  public String partnerItemId;

  /** Box item ID, for which the mappings should be returned. */
  public String boxItemId;

  /** Box item type, for which the mappings should be returned. */
  public EnumWrapper<GetSlackIntegrationMappingQueryParamsBoxItemTypeField> boxItemType;

  /** Whether the mapping has been manually created. */
  public Boolean isManuallyCreated;

  public GetSlackIntegrationMappingQueryParams() {}

  protected GetSlackIntegrationMappingQueryParams(Builder builder) {
    this.marker = builder.marker;
    this.limit = builder.limit;
    this.partnerItemType = builder.partnerItemType;
    this.partnerItemId = builder.partnerItemId;
    this.boxItemId = builder.boxItemId;
    this.boxItemType = builder.boxItemType;
    this.isManuallyCreated = builder.isManuallyCreated;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public EnumWrapper<GetSlackIntegrationMappingQueryParamsPartnerItemTypeField>
      getPartnerItemType() {
    return partnerItemType;
  }

  public String getPartnerItemId() {
    return partnerItemId;
  }

  public String getBoxItemId() {
    return boxItemId;
  }

  public EnumWrapper<GetSlackIntegrationMappingQueryParamsBoxItemTypeField> getBoxItemType() {
    return boxItemType;
  }

  public Boolean getIsManuallyCreated() {
    return isManuallyCreated;
  }

  public static class Builder {

    protected String marker;

    protected Long limit;

    protected EnumWrapper<GetSlackIntegrationMappingQueryParamsPartnerItemTypeField>
        partnerItemType;

    protected String partnerItemId;

    protected String boxItemId;

    protected EnumWrapper<GetSlackIntegrationMappingQueryParamsBoxItemTypeField> boxItemType;

    protected Boolean isManuallyCreated;

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Builder partnerItemType(
        GetSlackIntegrationMappingQueryParamsPartnerItemTypeField partnerItemType) {
      this.partnerItemType =
          new EnumWrapper<GetSlackIntegrationMappingQueryParamsPartnerItemTypeField>(
              partnerItemType);
      return this;
    }

    public Builder partnerItemType(
        EnumWrapper<GetSlackIntegrationMappingQueryParamsPartnerItemTypeField> partnerItemType) {
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

    public Builder boxItemType(GetSlackIntegrationMappingQueryParamsBoxItemTypeField boxItemType) {
      this.boxItemType =
          new EnumWrapper<GetSlackIntegrationMappingQueryParamsBoxItemTypeField>(boxItemType);
      return this;
    }

    public Builder boxItemType(
        EnumWrapper<GetSlackIntegrationMappingQueryParamsBoxItemTypeField> boxItemType) {
      this.boxItemType = boxItemType;
      return this;
    }

    public Builder isManuallyCreated(Boolean isManuallyCreated) {
      this.isManuallyCreated = isManuallyCreated;
      return this;
    }

    public GetSlackIntegrationMappingQueryParams build() {
      return new GetSlackIntegrationMappingQueryParams(this);
    }
  }
}
