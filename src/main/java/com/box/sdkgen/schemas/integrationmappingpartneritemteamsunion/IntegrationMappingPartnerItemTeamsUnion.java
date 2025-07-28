package com.box.sdkgen.schemas.integrationmappingpartneritemteamsunion;

import com.box.sdkgen.internal.OneOfOne;
import com.box.sdkgen.schemas.integrationmappingpartneritemteams.IntegrationMappingPartnerItemTeams;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.JsonManager;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;

@JsonDeserialize(
    using =
        IntegrationMappingPartnerItemTeamsUnion.IntegrationMappingPartnerItemTeamsUnionDeserializer
            .class)
@JsonSerialize(using = OneOfOne.OneOfOneSerializer.class)
public class IntegrationMappingPartnerItemTeamsUnion
    extends OneOfOne<IntegrationMappingPartnerItemTeams> {

  protected final String type;

  protected final String id;

  protected final String tenantId;

  public IntegrationMappingPartnerItemTeamsUnion(
      IntegrationMappingPartnerItemTeams integrationMappingPartnerItemTeams) {
    super(integrationMappingPartnerItemTeams);
    this.type = EnumWrapper.convertToString(integrationMappingPartnerItemTeams.getType());
    this.id = integrationMappingPartnerItemTeams.getId();
    this.tenantId = integrationMappingPartnerItemTeams.getTenantId();
  }

  public boolean isIntegrationMappingPartnerItemTeams() {
    return value0 != null;
  }

  public IntegrationMappingPartnerItemTeams getIntegrationMappingPartnerItemTeams() {
    return value0;
  }

  public String getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  public String getTenantId() {
    return tenantId;
  }

  static class IntegrationMappingPartnerItemTeamsUnionDeserializer
      extends JsonDeserializer<IntegrationMappingPartnerItemTeamsUnion> {

    public IntegrationMappingPartnerItemTeamsUnionDeserializer() {
      super();
    }

    @Override
    public IntegrationMappingPartnerItemTeamsUnion deserialize(
        JsonParser jp, DeserializationContext ctxt) throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "channel":
            return new IntegrationMappingPartnerItemTeamsUnion(
                JsonManager.deserialize(node, IntegrationMappingPartnerItemTeams.class));
          case "team":
            return new IntegrationMappingPartnerItemTeamsUnion(
                JsonManager.deserialize(node, IntegrationMappingPartnerItemTeams.class));
        }
      }
      throw new JsonMappingException(
          jp, "Unable to deserialize IntegrationMappingPartnerItemTeamsUnion");
    }
  }
}
