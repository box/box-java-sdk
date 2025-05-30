package com.box.sdkgen.schemas.aillmendpointparams;

import com.box.sdkgen.internal.OneOfFour;
import com.box.sdkgen.schemas.aillmendpointparamsaws.AiLlmEndpointParamsAws;
import com.box.sdkgen.schemas.aillmendpointparamsgoogle.AiLlmEndpointParamsGoogle;
import com.box.sdkgen.schemas.aillmendpointparamsibm.AiLlmEndpointParamsIbm;
import com.box.sdkgen.schemas.aillmendpointparamsopenai.AiLlmEndpointParamsOpenAi;
import com.box.sdkgen.serialization.json.JsonManager;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;

@JsonDeserialize(using = AiLlmEndpointParams.AiLlmEndpointParamsDeserializer.class)
@JsonSerialize(using = OneOfFour.OneOfFourSerializer.class)
public class AiLlmEndpointParams
    extends OneOfFour<
        AiLlmEndpointParamsOpenAi,
        AiLlmEndpointParamsGoogle,
        AiLlmEndpointParamsAws,
        AiLlmEndpointParamsIbm> {

  public AiLlmEndpointParams(AiLlmEndpointParamsOpenAi aiLlmEndpointParamsOpenAi) {
    super(aiLlmEndpointParamsOpenAi, null, null, null);
  }

  public AiLlmEndpointParams(AiLlmEndpointParamsGoogle aiLlmEndpointParamsGoogle) {
    super(null, aiLlmEndpointParamsGoogle, null, null);
  }

  public AiLlmEndpointParams(AiLlmEndpointParamsAws aiLlmEndpointParamsAws) {
    super(null, null, aiLlmEndpointParamsAws, null);
  }

  public AiLlmEndpointParams(AiLlmEndpointParamsIbm aiLlmEndpointParamsIbm) {
    super(null, null, null, aiLlmEndpointParamsIbm);
  }

  public AiLlmEndpointParamsOpenAi getAiLlmEndpointParamsOpenAi() {
    return value0;
  }

  public AiLlmEndpointParamsGoogle getAiLlmEndpointParamsGoogle() {
    return value1;
  }

  public AiLlmEndpointParamsAws getAiLlmEndpointParamsAws() {
    return value2;
  }

  public AiLlmEndpointParamsIbm getAiLlmEndpointParamsIbm() {
    return value3;
  }

  static class AiLlmEndpointParamsDeserializer extends JsonDeserializer<AiLlmEndpointParams> {

    public AiLlmEndpointParamsDeserializer() {
      super();
    }

    @Override
    public AiLlmEndpointParams deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      JsonNode node = JsonManager.jsonToSerializedData(jp);
      JsonNode discriminant0 = node.get("type");
      if (!(discriminant0 == null)) {
        switch (discriminant0.asText()) {
          case "openai_params":
            return new AiLlmEndpointParams(
                JsonManager.deserialize(node, AiLlmEndpointParamsOpenAi.class));
          case "google_params":
            return new AiLlmEndpointParams(
                JsonManager.deserialize(node, AiLlmEndpointParamsGoogle.class));
          case "aws_params":
            return new AiLlmEndpointParams(
                JsonManager.deserialize(node, AiLlmEndpointParamsAws.class));
          case "ibm_params":
            return new AiLlmEndpointParams(
                JsonManager.deserialize(node, AiLlmEndpointParamsIbm.class));
        }
      }
      throw new JsonMappingException(jp, "Unable to deserialize AiLlmEndpointParams");
    }
  }
}
