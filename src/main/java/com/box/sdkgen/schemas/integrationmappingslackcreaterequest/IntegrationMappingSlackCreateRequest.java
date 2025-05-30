package com.box.sdkgen.schemas.integrationmappingslackcreaterequest;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.integrationmappingboxitemslack.IntegrationMappingBoxItemSlack;
import com.box.sdkgen.schemas.integrationmappingpartneritemslack.IntegrationMappingPartnerItemSlack;
import com.box.sdkgen.schemas.integrationmappingslackoptions.IntegrationMappingSlackOptions;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class IntegrationMappingSlackCreateRequest extends SerializableObject {

  @JsonProperty("partner_item")
  protected final IntegrationMappingPartnerItemSlack partnerItem;

  @JsonProperty("box_item")
  protected final IntegrationMappingBoxItemSlack boxItem;

  protected IntegrationMappingSlackOptions options;

  public IntegrationMappingSlackCreateRequest(
      @JsonProperty("partner_item") IntegrationMappingPartnerItemSlack partnerItem,
      @JsonProperty("box_item") IntegrationMappingBoxItemSlack boxItem) {
    super();
    this.partnerItem = partnerItem;
    this.boxItem = boxItem;
  }

  protected IntegrationMappingSlackCreateRequest(
      IntegrationMappingSlackCreateRequestBuilder builder) {
    super();
    this.partnerItem = builder.partnerItem;
    this.boxItem = builder.boxItem;
    this.options = builder.options;
  }

  public IntegrationMappingPartnerItemSlack getPartnerItem() {
    return partnerItem;
  }

  public IntegrationMappingBoxItemSlack getBoxItem() {
    return boxItem;
  }

  public IntegrationMappingSlackOptions getOptions() {
    return options;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IntegrationMappingSlackCreateRequest casted = (IntegrationMappingSlackCreateRequest) o;
    return Objects.equals(partnerItem, casted.partnerItem)
        && Objects.equals(boxItem, casted.boxItem)
        && Objects.equals(options, casted.options);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partnerItem, boxItem, options);
  }

  @Override
  public String toString() {
    return "IntegrationMappingSlackCreateRequest{"
        + "partnerItem='"
        + partnerItem
        + '\''
        + ", "
        + "boxItem='"
        + boxItem
        + '\''
        + ", "
        + "options='"
        + options
        + '\''
        + "}";
  }

  public static class IntegrationMappingSlackCreateRequestBuilder {

    protected final IntegrationMappingPartnerItemSlack partnerItem;

    protected final IntegrationMappingBoxItemSlack boxItem;

    protected IntegrationMappingSlackOptions options;

    public IntegrationMappingSlackCreateRequestBuilder(
        IntegrationMappingPartnerItemSlack partnerItem, IntegrationMappingBoxItemSlack boxItem) {
      this.partnerItem = partnerItem;
      this.boxItem = boxItem;
    }

    public IntegrationMappingSlackCreateRequestBuilder options(
        IntegrationMappingSlackOptions options) {
      this.options = options;
      return this;
    }

    public IntegrationMappingSlackCreateRequest build() {
      return new IntegrationMappingSlackCreateRequest(this);
    }
  }
}
