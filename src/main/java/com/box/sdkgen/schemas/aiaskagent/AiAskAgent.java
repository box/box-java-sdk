package com.box.sdkgen.schemas.aiaskagent;

import com.box.sdkgen.internal.OneOfTwo;
import com.box.sdkgen.schemas.aiagentask.AiAgentAsk;
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

@JsonDeserialize(using = AiAskAgent.AiAskAgentDeserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class AiAskAgent extends OneOfTwo<AiAgentReference, AiAgentAsk> {

  protected final String type;

  public AiAskAgent(AiAgentReference aiAgentReference) {
    super(aiAgentReference, null);
    this.type = EnumWrapper.convertToString(aiAgentReference.getType());
  }

  public AiAskAgent(AiAgentAsk aiAgentAsk) {
    super(null, aiAgentAsk);
    this.type = EnumWrapper.convertToString(aiAgentAsk.getType());
  }

  public boolean isAiAgentReference() {
    return value0 != null;
  }

  public AiAgentReference getAiAgentReference() {
    return value0;
  }

  public boolean isAiAgentAsk() {
    return value1 != null;
  }

  public AiAgentAsk getAiAgentAsk() {
    return value1;
  }

  public String getType() {
    return type;
  }

  static class AiAskAgentDeserializer extends JsonDeserializer<AiAskAgent> {

    public AiAskAgentDeserializer() {
      super();
    }

    @Override
    public AiAskAgent deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "ai_agent_id":
            return new AiAskAgent(JsonManager.deserialize(node, AiAgentReference.class));
          case "ai_agent_ask":
            return new AiAskAgent(JsonManager.deserialize(node, AiAgentAsk.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize AiAskAgent");
    }
  }
}
