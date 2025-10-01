package com.box.sdkgen.schemas.integrationmappingteamscreaterequest;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.folderreference.FolderReference;
import com.box.sdkgen.schemas.integrationmappingpartneritemteamscreaterequest.IntegrationMappingPartnerItemTeamsCreateRequest;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** A request to create a Teams Integration Mapping object. */
@JsonFilter("nullablePropertyFilter")
public class IntegrationMappingTeamsCreateRequest extends SerializableObject {

  @JsonProperty("partner_item")
  protected final IntegrationMappingPartnerItemTeamsCreateRequest partnerItem;

  @JsonProperty("box_item")
  protected final FolderReference boxItem;

  public IntegrationMappingTeamsCreateRequest(
      @JsonProperty("partner_item") IntegrationMappingPartnerItemTeamsCreateRequest partnerItem,
      @JsonProperty("box_item") FolderReference boxItem) {
    super();
    this.partnerItem = partnerItem;
    this.boxItem = boxItem;
  }

  public IntegrationMappingPartnerItemTeamsCreateRequest getPartnerItem() {
    return partnerItem;
  }

  public FolderReference getBoxItem() {
    return boxItem;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IntegrationMappingTeamsCreateRequest casted = (IntegrationMappingTeamsCreateRequest) o;
    return Objects.equals(partnerItem, casted.partnerItem)
        && Objects.equals(boxItem, casted.boxItem);
  }

  @Override
  public int hashCode() {
    return Objects.hash(partnerItem, boxItem);
  }

  @Override
  public String toString() {
    return "IntegrationMappingTeamsCreateRequest{"
        + "partnerItem='"
        + partnerItem
        + '\''
        + ", "
        + "boxItem='"
        + boxItem
        + '\''
        + "}";
  }
}
