package com.box.sdkgen.schemas.aiagentaskoraiagentextractoraiagentextractstructuredoraiagenttextgen;

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

@JsonDeserialize(
    using =
        AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen
            .AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGenDeserializer.class)
@JsonSerialize(using = OneOfFour.OneOfFourSerializer.class)
public class AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen
    extends OneOfFour<AiAgentAsk, AiAgentExtract, AiAgentExtractStructured, AiAgentTextGen> {

  protected final String type;

  public AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen(
      AiAgentAsk aiAgentAsk) {
    super(aiAgentAsk, null, null, null);
    this.type = EnumWrapper.convertToString(aiAgentAsk.getType());
  }

  public AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen(
      AiAgentExtract aiAgentExtract) {
    super(null, aiAgentExtract, null, null);
    this.type = EnumWrapper.convertToString(aiAgentExtract.getType());
  }

  public AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen(
      AiAgentExtractStructured aiAgentExtractStructured) {
    super(null, null, aiAgentExtractStructured, null);
    this.type = EnumWrapper.convertToString(aiAgentExtractStructured.getType());
  }

  public AiAgentAskOrAiAgentExtractOrAiAgentExtractStructuredOrAiAgentTextGen(
      AiAgentTextGen aiAgentTextGen) {
    super(null, null, null, aiAgentTextGen);
    this.type = EnumWrapper.convertToString(aiAgentTextGen.getType());
  }

  public boolean isAiAgentAsk() {
    return value0 != null;
  }

  public AiAgentAsk getAiAgentAsk() {
    return value0;
  }

  public boolean isAiAgentExtract() {
    return value1 != null;
  }

  public AiAgentExtract getAiAgentExtract() {
    return value1;
  }

  public boolean isAiAgentExtractStructured() {
    return value2 != null;
  }

  public AiAgentExtractStructured getAiAgentExtractStructured() {
    return value2;
  }

  public boolean isAiAgentTextGen() {
    return value3 != null;
  }

  public AiAgentTextGen getAiAgentTextGen() {
    return value3;
  }

  public String getType() {
    return type;
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
