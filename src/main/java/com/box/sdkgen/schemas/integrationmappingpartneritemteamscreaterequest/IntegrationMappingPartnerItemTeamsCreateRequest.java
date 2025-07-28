package com.box.sdkgen.schemas.integrationmappingpartneritemteamscreaterequest;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class IntegrationMappingPartnerItemTeamsCreateRequest extends SerializableObject {

  @JsonDeserialize(
      using =
          IntegrationMappingPartnerItemTeamsCreateRequestTypeField
              .IntegrationMappingPartnerItemTeamsCreateRequestTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          IntegrationMappingPartnerItemTeamsCreateRequestTypeField
              .IntegrationMappingPartnerItemTeamsCreateRequestTypeFieldSerializer.class)
  protected final EnumWrapper<IntegrationMappingPartnerItemTeamsCreateRequestTypeField> type;

  protected final String id;

  @JsonProperty("tenant_id")
  protected final String tenantId;

  @JsonProperty("team_id")
  protected final String teamId;

  public IntegrationMappingPartnerItemTeamsCreateRequest(
      IntegrationMappingPartnerItemTeamsCreateRequestTypeField type,
      String id,
      String tenantId,
      String teamId) {
    super();
    this.type = new EnumWrapper<IntegrationMappingPartnerItemTeamsCreateRequestTypeField>(type);
    this.id = id;
    this.tenantId = tenantId;
    this.teamId = teamId;
  }

  public IntegrationMappingPartnerItemTeamsCreateRequest(
      @JsonProperty("type")
          EnumWrapper<IntegrationMappingPartnerItemTeamsCreateRequestTypeField> type,
      @JsonProperty("id") String id,
      @JsonProperty("tenant_id") String tenantId,
      @JsonProperty("team_id") String teamId) {
    super();
    this.type = type;
    this.id = id;
    this.tenantId = tenantId;
    this.teamId = teamId;
  }

  public EnumWrapper<IntegrationMappingPartnerItemTeamsCreateRequestTypeField> getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  public String getTenantId() {
    return tenantId;
  }

  public String getTeamId() {
    return teamId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IntegrationMappingPartnerItemTeamsCreateRequest casted =
        (IntegrationMappingPartnerItemTeamsCreateRequest) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(id, casted.id)
        && Objects.equals(tenantId, casted.tenantId)
        && Objects.equals(teamId, casted.teamId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id, tenantId, teamId);
  }

  @Override
  public String toString() {
    return "IntegrationMappingPartnerItemTeamsCreateRequest{"
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
        + ", "
        + "teamId='"
        + teamId
        + '\''
        + "}";
  }
}
