package com.box.sdkgen.schemas.aiagentreferenceoraiagenttextgen;

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

@JsonDeserialize(
    using = AiAgentReferenceOrAiAgentTextGen.AiAgentReferenceOrAiAgentTextGenDeserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class AiAgentReferenceOrAiAgentTextGen extends OneOfTwo<AiAgentReference, AiAgentTextGen> {

  protected final String type;

  public AiAgentReferenceOrAiAgentTextGen(AiAgentReference aiAgentReference) {
    super(aiAgentReference, null);
    this.type = EnumWrapper.convertToString(aiAgentReference.getType());
  }

  public AiAgentReferenceOrAiAgentTextGen(AiAgentTextGen aiAgentTextGen) {
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

  static class AiAgentReferenceOrAiAgentTextGenDeserializer
      extends JsonDeserializer<AiAgentReferenceOrAiAgentTextGen> {

    public AiAgentReferenceOrAiAgentTextGenDeserializer() {
      super();
    }

    @Override
    public AiAgentReferenceOrAiAgentTextGen deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "ai_agent_id":
            return new AiAgentReferenceOrAiAgentTextGen(
                JsonManager.deserialize(node, AiAgentReference.class));
          case "ai_agent_text_gen":
            return new AiAgentReferenceOrAiAgentTextGen(
                JsonManager.deserialize(node, AiAgentTextGen.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize AiAgentReferenceOrAiAgentTextGen");
    }
  }
}
