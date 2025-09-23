package com.box.sdkgen.schemas.integrationmappingpartneritemteams;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class IntegrationMappingPartnerItemTeams extends SerializableObject {

  @JsonDeserialize(
      using =
          IntegrationMappingPartnerItemTeamsTypeField
              .IntegrationMappingPartnerItemTeamsTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          IntegrationMappingPartnerItemTeamsTypeField
              .IntegrationMappingPartnerItemTeamsTypeFieldSerializer.class)
  protected final EnumWrapper<IntegrationMappingPartnerItemTeamsTypeField> type;

  protected final String id;

  @JsonProperty("tenant_id")
  protected final String tenantId;

  public IntegrationMappingPartnerItemTeams(
      IntegrationMappingPartnerItemTeamsTypeField type, String id, String tenantId) {
    super();
    this.type = new EnumWrapper<IntegrationMappingPartnerItemTeamsTypeField>(type);
    this.id = id;
    this.tenantId = tenantId;
  }

  public IntegrationMappingPartnerItemTeams(
      @JsonProperty("type") EnumWrapper<IntegrationMappingPartnerItemTeamsTypeField> type,
      @JsonProperty("id") String id,
      @JsonProperty("tenant_id") String tenantId) {
    super();
    this.type = type;
    this.id = id;
    this.tenantId = tenantId;
  }

  public EnumWrapper<IntegrationMappingPartnerItemTeamsTypeField> getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  public String getTenantId() {
    return tenantId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IntegrationMappingPartnerItemTeams casted = (IntegrationMappingPartnerItemTeams) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(id, casted.id)
        && Objects.equals(tenantId, casted.tenantId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id, tenantId);
  }

  @Override
  public String toString() {
    return "IntegrationMappingPartnerItemTeams{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + ", "
        + "tenantId='"
        + tenantId
        + '\''
        + "}";
  }
}
