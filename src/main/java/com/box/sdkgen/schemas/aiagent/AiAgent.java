package com.box.sdkgen.schemas.aiagent;

import com.box.sdkgen.internal.OneOfFour;
import com.box.sdkgen.schemas.aiagentask.AiAgentAsk;
import com.box.sdkgen.schemas.aiagentextract.AiAgentExtract;
import com.box.sdkgen.schemas.aiagentextractstructured.AiAgentExtractStructured;
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

@JsonDeserialize(using = AiAgent.AiAgentDeserializer.class)
@JsonSerialize(using = OneOfFour.OneOfFourSerializer.class)
public class AiAgent
    extends OneOfFour<AiAgentAsk, AiAgentTextGen, AiAgentExtract, AiAgentExtractStructured> {

  protected final String type;

  public AiAgent(AiAgentAsk aiAgentAsk) {
    super(aiAgentAsk, null, null, null);
    this.type = EnumWrapper.convertToString(aiAgentAsk.getType());
  }

  public AiAgent(AiAgentTextGen aiAgentTextGen) {
    super(null, aiAgentTextGen, null, null);
    this.type = EnumWrapper.convertToString(aiAgentTextGen.getType());
  }

  public AiAgent(AiAgentExtract aiAgentExtract) {
    super(null, null, aiAgentExtract, null);
    this.type = EnumWrapper.convertToString(aiAgentExtract.getType());
  }

  public AiAgent(AiAgentExtractStructured aiAgentExtractStructured) {
    super(null, null, null, aiAgentExtractStructured);
    this.type = EnumWrapper.convertToString(aiAgentExtractStructured.getType());
  }

  public boolean isAiAgentAsk() {
    return value0 != null;
  }

  public AiAgentAsk getAiAgentAsk() {
    return value0;
  }

  public boolean isAiAgentTextGen() {
    return value1 != null;
  }

  public AiAgentTextGen getAiAgentTextGen() {
    return value1;
  }

  public boolean isAiAgentExtract() {
    return value2 != null;
  }

  public AiAgentExtract getAiAgentExtract() {
    return value2;
  }

  public boolean isAiAgentExtractStructured() {
    return value3 != null;
  }

  public AiAgentExtractStructured getAiAgentExtractStructured() {
    return value3;
  }

  public String getType() {
    return type;
  }

  static class AiAgentDeserializer extends JsonDeserializer<AiAgent> {

    public AiAgentDeserializer() {
      super();
    }

    @Override
    public AiAgent deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "ai_agent_ask":
            return new AiAgent(JsonManager.deserialize(node, AiAgentAsk.class));
          case "ai_agent_text_gen":
            return new AiAgent(JsonManager.deserialize(node, AiAgentTextGen.class));
          case "ai_agent_extract":
            return new AiAgent(JsonManager.deserialize(node, AiAgentExtract.class));
          case "ai_agent_extract_structured":
            return new AiAgent(JsonManager.deserialize(node, AiAgentExtractStructured.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize AiAgent");
    }
  }
}
