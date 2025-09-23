package com.box.sdkgen.schemas.aiextractstructuredagent;

import com.box.sdkgen.internal.OneOfTwo;
import com.box.sdkgen.schemas.aiagentextractstructured.AiAgentExtractStructured;
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

@JsonDeserialize(using = AiExtractStructuredAgent.AiExtractStructuredAgentDeserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class AiExtractStructuredAgent extends OneOfTwo<AiAgentReference, AiAgentExtractStructured> {

  protected final String type;

  public AiExtractStructuredAgent(AiAgentReference aiAgentReference) {
    super(aiAgentReference, null);
    this.type = EnumWrapper.convertToString(aiAgentReference.getType());
  }

  public AiExtractStructuredAgent(AiAgentExtractStructured aiAgentExtractStructured) {
    super(null, aiAgentExtractStructured);
    this.type = EnumWrapper.convertToString(aiAgentExtractStructured.getType());
  }

  public boolean isAiAgentReference() {
    return value0 != null;
  }

  public AiAgentReference getAiAgentReference() {
    return value0;
  }

  public boolean isAiAgentExtractStructured() {
    return value1 != null;
  }

  public AiAgentExtractStructured getAiAgentExtractStructured() {
    return value1;
  }

  public String getType() {
    return type;
  }

  static class AiExtractStructuredAgentDeserializer
      extends JsonDeserializer<AiExtractStructuredAgent> {

    public AiExtractStructuredAgentDeserializer() {
      super();
    }

    @Override
    public AiExtractStructuredAgent deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "ai_agent_id":
            return new AiExtractStructuredAgent(
                JsonManager.deserialize(node, AiAgentReference.class));
          case "ai_agent_extract_structured":
            return new AiExtractStructuredAgent(
                JsonManager.deserialize(node, AiAgentExtractStructured.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize AiExtractStructuredAgent");
    }
  }
}
