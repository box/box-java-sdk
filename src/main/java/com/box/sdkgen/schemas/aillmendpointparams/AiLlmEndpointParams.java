package com.box.sdkgen.schemas.aillmendpointparams;

import com.box.sdkgen.internal.OneOfFour;
import com.box.sdkgen.schemas.aillmendpointparamsaws.AiLlmEndpointParamsAws;
import com.box.sdkgen.schemas.aillmendpointparamsgoogle.AiLlmEndpointParamsGoogle;
import com.box.sdkgen.schemas.aillmendpointparamsibm.AiLlmEndpointParamsIbm;
import com.box.sdkgen.schemas.aillmendpointparamsopenai.AiLlmEndpointParamsOpenAi;
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

@JsonDeserialize(using = AiLlmEndpointParams.AiLlmEndpointParamsDeserializer.class)
@JsonSerialize(using = OneOfFour.OneOfFourSerializer.class)
public class AiLlmEndpointParams
    extends OneOfFour<
        AiLlmEndpointParamsOpenAi,
        AiLlmEndpointParamsGoogle,
        AiLlmEndpointParamsAws,
        AiLlmEndpointParamsIbm> {

  protected final String type;

  protected final Double temperature;

  protected final Double topP;

  public AiLlmEndpointParams(AiLlmEndpointParamsOpenAi aiLlmEndpointParamsOpenAi) {
    super(aiLlmEndpointParamsOpenAi, null, null, null);
    this.type = EnumWrapper.convertToString(aiLlmEndpointParamsOpenAi.getType());
    this.temperature = aiLlmEndpointParamsOpenAi.getTemperature();
    this.topP = aiLlmEndpointParamsOpenAi.getTopP();
  }

  public AiLlmEndpointParams(AiLlmEndpointParamsGoogle aiLlmEndpointParamsGoogle) {
    super(null, aiLlmEndpointParamsGoogle, null, null);
    this.type = EnumWrapper.convertToString(aiLlmEndpointParamsGoogle.getType());
    this.temperature = aiLlmEndpointParamsGoogle.getTemperature();
    this.topP = aiLlmEndpointParamsGoogle.getTopP();
  }

  public AiLlmEndpointParams(AiLlmEndpointParamsAws aiLlmEndpointParamsAws) {
    super(null, null, aiLlmEndpointParamsAws, null);
    this.type = EnumWrapper.convertToString(aiLlmEndpointParamsAws.getType());
    this.temperature = aiLlmEndpointParamsAws.getTemperature();
    this.topP = aiLlmEndpointParamsAws.getTopP();
  }

  public AiLlmEndpointParams(AiLlmEndpointParamsIbm aiLlmEndpointParamsIbm) {
    super(null, null, null, aiLlmEndpointParamsIbm);
    this.type = EnumWrapper.convertToString(aiLlmEndpointParamsIbm.getType());
    this.temperature = aiLlmEndpointParamsIbm.getTemperature();
    this.topP = aiLlmEndpointParamsIbm.getTopP();
  }

  public boolean isAiLlmEndpointParamsOpenAi() {
    return value0 != null;
  }

  public AiLlmEndpointParamsOpenAi getAiLlmEndpointParamsOpenAi() {
    return value0;
  }

  public boolean isAiLlmEndpointParamsGoogle() {
    return value1 != null;
  }

  public AiLlmEndpointParamsGoogle getAiLlmEndpointParamsGoogle() {
    return value1;
  }

  public boolean isAiLlmEndpointParamsAws() {
    return value2 != null;
  }

  public AiLlmEndpointParamsAws getAiLlmEndpointParamsAws() {
    return value2;
  }

  public boolean isAiLlmEndpointParamsIbm() {
    return value3 != null;
  }

  public AiLlmEndpointParamsIbm getAiLlmEndpointParamsIbm() {
    return value3;
  }

  public String getType() {
    return type;
  }

  public double getTemperature() {
    return temperature;
  }

  public double getTopP() {
    return topP;
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
