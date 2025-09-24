package com.box.sdkgen.schemas.aiextractagent;

import com.box.sdkgen.internal.OneOfTwo;
import com.box.sdkgen.schemas.aiagentextract.AiAgentExtract;
import com.box.sdkgen.schemas.aiagentreference.AiAgentReference;
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

@JsonDeserialize(using = AiExtractAgent.AiExtractAgentDeserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class AiExtractAgent extends OneOfTwo<AiAgentReference, AiAgentExtract> {

  protected final String type;

  public AiExtractAgent(AiAgentReference aiAgentReference) {
    super(aiAgentReference, null);
    this.type = EnumWrapper.convertToString(aiAgentReference.getType());
  }

  public AiExtractAgent(AiAgentExtract aiAgentExtract) {
    super(null, aiAgentExtract);
    this.type = EnumWrapper.convertToString(aiAgentExtract.getType());
  }

  public boolean isAiAgentReference() {
    return value0 != null;
  }

  public AiAgentReference getAiAgentReference() {
    return value0;
  }

  public boolean isAiAgentExtract() {
    return value1 != null;
  }

  public AiAgentExtract getAiAgentExtract() {
    return value1;
  }

  public String getType() {
    return type;
  }

  static class AiExtractAgentDeserializer extends JsonDeserializer<AiExtractAgent> {

    public AiExtractAgentDeserializer() {
      super();
    }

    @Override
    public AiExtractAgent deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "ai_agent_id":
            return new AiExtractAgent(JsonManager.deserialize(node, AiAgentReference.class));
          case "ai_agent_extract":
            return new AiExtractAgent(JsonManager.deserialize(node, AiAgentExtract.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize AiExtractAgent");
    }
  }
}
