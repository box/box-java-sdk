package com.box.sdkgen.schemas.aiagentextractoraiagentreference;

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

@JsonDeserialize(
    using = AiAgentExtractOrAiAgentReference.AiAgentExtractOrAiAgentReferenceDeserializer.class)
@JsonSerialize(using = OneOfTwo.OneOfTwoSerializer.class)
public class AiAgentExtractOrAiAgentReference extends OneOfTwo<AiAgentExtract, AiAgentReference> {

  protected final String type;

  public AiAgentExtractOrAiAgentReference(AiAgentExtract aiAgentExtract) {
    super(aiAgentExtract, null);
    this.type = EnumWrapper.convertToString(aiAgentExtract.getType());
  }

  public AiAgentExtractOrAiAgentReference(AiAgentReference aiAgentReference) {
    super(null, aiAgentReference);
    this.type = EnumWrapper.convertToString(aiAgentReference.getType());
  }

  public boolean isAiAgentExtract() {
    return value0 != null;
  }

  public AiAgentExtract getAiAgentExtract() {
    return value0;
  }

  public boolean isAiAgentReference() {
    return value1 != null;
  }

  public AiAgentReference getAiAgentReference() {
    return value1;
  }

  public String getType() {
    return type;
  }

  static class AiAgentExtractOrAiAgentReferenceDeserializer
      extends JsonDeserializer<AiAgentExtractOrAiAgentReference> {

    public AiAgentExtractOrAiAgentReferenceDeserializer() {
      super();
    }

    @Override
    public AiAgentExtractOrAiAgentReference deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "ai_agent_extract":
            return new AiAgentExtractOrAiAgentReference(
                JsonManager.deserialize(node, AiAgentExtract.class));
          case "ai_agent_id":
            return new AiAgentExtractOrAiAgentReference(
                JsonManager.deserialize(node, AiAgentReference.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize AiAgentExtractOrAiAgentReference");
    }
  }
}
