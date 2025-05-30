package com.box.sdkgen.schemas.aiagentaskoraiagentreference;

import com.box.sdkgen.internal.OneOfTwo;
import com.box.sdkgen.schemas.aiagentask.AiAgentAsk;
import com.box.sdkgen.schemas.aiagentreference.AiAgentReference;
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
    using = AiAgentAskOrAiAgentReference.AiAgentAskOrAiAgentReferenceDeserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class AiAgentAskOrAiAgentReference extends OneOfTwo<AiAgentAsk, AiAgentReference> {

  public AiAgentAskOrAiAgentReference(AiAgentAsk aiAgentAsk) {
    super(aiAgentAsk, null);
  }

  public AiAgentAskOrAiAgentReference(AiAgentReference aiAgentReference) {
    super(null, aiAgentReference);
  }

  public AiAgentAsk getAiAgentAsk() {
    return value0;
  }

  public AiAgentReference getAiAgentReference() {
    return value1;
  }

  static class AiAgentAskOrAiAgentReferenceDeserializer
      extends JsonDeserializer<AiAgentAskOrAiAgentReference> {

    public AiAgentAskOrAiAgentReferenceDeserializer() {
      super();
    }

    @Override
    public AiAgentAskOrAiAgentReference deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "ai_agent_ask":
            return new AiAgentAskOrAiAgentReference(
                JsonManager.deserialize(node, AiAgentAsk.class));
          case "ai_agent_id":
            return new AiAgentAskOrAiAgentReference(
                JsonManager.deserialize(node, AiAgentReference.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize AiAgentAskOrAiAgentReference");
    }
  }
}
