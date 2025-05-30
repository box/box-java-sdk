package com.box.sdkgen.schemas.aiagentaskoraiagentextractoraiagentextractstructuredoraiagenttextgen;

import com.box.sdkgen.internal.OneOfFour;
import com.box.sdkgen.schemas.aiagentask.AiAgentAsk;
import com.box.sdkgen.schemas.aiagentextract.AiAgentExtract;
import com.box.sdkgen.schemas.aiagentextractstructured.AiAgentExtractStructured;
import com.box.sdkgen.schemas.aiagenttextgen.AiAgentTextGen;
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
        AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen
            .AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGenDeserializer.class)
@JsonSerialize(using = OneOfFour.OneOfFourSerializer.class)
public class AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen
    extends OneOfFour<AiAgentAsk, AiAgentExtract, AiAgentExtractStructured, AiAgentTextGen> {

  public AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen(
      AiAgentAsk aiAgentAsk) {
    super(aiAgentAsk, null, null, null);
  }

  public AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen(
      AiAgentExtract aiAgentExtract) {
    super(null, aiAgentExtract, null, null);
  }

  public AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen(
      AiAgentExtractStructured aiAgentExtractStructured) {
    super(null, null, aiAgentExtractStructured, null);
  }

  public AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen(
      AiAgentTextGen aiAgentTextGen) {
    super(null, null, null, aiAgentTextGen);
  }

  public AiAgentAsk getAiAgentAsk() {
    return value0;
  }

  public AiAgentExtract getAiAgentExtract() {
    return value1;
  }

  public AiAgentExtractStructured getAiAgentExtractStructured() {
    return value2;
  }

  public AiAgentTextGen getAiAgentTextGen() {
    return value3;
  }

  static class AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGenDeserializer
      extends JsonDeserializer<
          AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen> {

    public AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGenDeserializer() {
      super();
    }

    @Override
    public AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen deserialize(
        JsonParser jp, DeserializationContext ctxt) throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "ai_agent_ask":
            return new AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen(
                JsonManager.deserialize(node, AiAgentAsk.class));
          case "ai_agent_extract":
            return new AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen(
                JsonManager.deserialize(node, AiAgentExtract.class));
          case "ai_agent_extract_structured":
            return new AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen(
                JsonManager.deserialize(node, AiAgentExtractStructured.class));
          case "ai_agent_text_gen":
            return new AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen(
                JsonManager.deserialize(node, AiAgentTextGen.class));
        }
      }
      throw new JsonMappingException(
          jp,
          "Unable to deserialize AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen");
    }
  }
}
