package com.box.sdkgen.schemas.aitextgenagent;

import com.box.sdkgen.internal.OneOfTwo;
import com.box.sdkgen.schemas.aiagentreference.AiAgentReference;
import com.box.sdkgen.schemas.aiagenttextgen.AiAgentTextGen;
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

@JsonDeserialize(using = AiTextGenAgent.AiTextGenAgentDeserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class AiTextGenAgent extends OneOfTwo<AiAgentReference, AiAgentTextGen> {

  protected final String type;

  public AiTextGenAgent(AiAgentReference aiAgentReference) {
    super(aiAgentReference, null);
    this.type = EnumWrapper.convertToString(aiAgentReference.getType());
  }

  public AiTextGenAgent(AiAgentTextGen aiAgentTextGen) {
    super(null, aiAgentTextGen);
    this.type = EnumWrapper.convertToString(aiAgentTextGen.getType());
  }

  public boolean isAiAgentReference() {
    return value0 != null;
  }

  public AiAgentReference getAiAgentReference() {
    return value0;
  }

  public boolean isAiAgentTextGen() {
    return value1 != null;
  }

  public AiAgentTextGen getAiAgentTextGen() {
    return value1;
  }

  public String getType() {
    return type;
  }

  static class AiTextGenAgentDeserializer extends JsonDeserializer<AiTextGenAgent> {

    public AiTextGenAgentDeserializer() {
      super();
    }

    @Override
    public AiTextGenAgent deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "ai_agent_id":
            return new AiTextGenAgent(JsonManager.deserialize(node, AiAgentReference.class));
          case "ai_agent_text_gen":
            return new AiTextGenAgent(JsonManager.deserialize(node, AiAgentTextGen.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize AiTextGenAgent");
    }
  }
}
