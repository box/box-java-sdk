package com.box.sdkgen.schemas.aiagentallowedentity;

import com.box.sdkgen.internal.OneOfTwo;
import com.box.sdkgen.schemas.groupbase.GroupBase;
import com.box.sdkgen.schemas.userbase.UserBase;
import com.box.sdkgen.serialization.json.JsonManager;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;

@JsonDeserialize(using = AiAgentAllowedEntity.AiAgentAllowedEntityDeserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class AiAgentAllowedEntity extends OneOfTwo<UserBase, GroupBase> {

  public AiAgentAllowedEntity(UserBase userBase) {
    super(userBase, null);
  }

  public AiAgentAllowedEntity(GroupBase groupBase) {
    super(null, groupBase);
  }

  public UserBase getUserBase() {
    return value0;
  }

  public GroupBase getGroupBase() {
    return value1;
  }

  static class AiAgentAllowedEntityDeserializer extends JsonDeserializer<AiAgentAllowedEntity> {

    public AiAgentAllowedEntityDeserializer() {
      super();
    }

    @Override
    public AiAgentAllowedEntity deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "user":
            return new AiAgentAllowedEntity(JsonManager.deserialize(node, UserBase.class));
          case "group":
            return new AiAgentAllowedEntity(JsonManager.deserialize(node, GroupBase.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize AiAgentAllowedEntity");
    }
  }
}
