package com.box.sdkgen.schemas.aillmendpointparamsgoogle;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class AiLlmEndpointParamsGoogle extends SerializableObject {

  @JsonDeserialize(
      using =
          AiLlmEndpointParamsGoogleTypeField.AiLlmEndpointParamsGoogleTypeFieldDeserializer.class)
  @JsonSerialize(
      using = AiLlmEndpointParamsGoogleTypeField.AiLlmEndpointParamsGoogleTypeFieldSerializer.class)
  protected EnumWrapper<AiLlmEndpointParamsGoogleTypeField> type;

  protected Double temperature;

  @JsonProperty("top_p")
  protected Double topP;

  @JsonProperty("top_k")
  protected Double topK;

  public AiLlmEndpointParamsGoogle() {
    super();
    this.type =
        new EnumWrapper<AiLlmEndpointParamsGoogleTypeField>(
            AiLlmEndpointParamsGoogleTypeField.GOOGLE_PARAMS);
  }

  protected AiLlmEndpointParamsGoogle(AiLlmEndpointParamsGoogleBuilder builder) {
    super();
    this.type = builder.type;
    this.temperature = builder.temperature;
    this.topP = builder.topP;
    this.topK = builder.topK;
  }

  public EnumWrapper<AiLlmEndpointParamsGoogleTypeField> getType() {
    return type;
  }

  public Double getTemperature() {
    return temperature;
  }

  public Double getTopP() {
    return topP;
  }

  public Double getTopK() {
    return topK;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiLlmEndpointParamsGoogle casted = (AiLlmEndpointParamsGoogle) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(temperature, casted.temperature)
        && Objects.equals(topP, casted.topP)
        && Objects.equals(topK, casted.topK);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, temperature, topP, topK);
  }

  @Override
  public String toString() {
    return "AiLlmEndpointParamsGoogle{"
        + "type='"
        + type
        + '\''
        + ", "
        + "temperature='"
        + temperature
        + '\''
        + ", "
        + "topP='"
        + topP
        + '\''
        + ", "
        + "topK='"
        + topK
        + '\''
        + "}";
  }

  public static class AiLlmEndpointParamsGoogleBuilder {

    protected EnumWrapper<AiLlmEndpointParamsGoogleTypeField> type;

    protected Double temperature;

    protected Double topP;

    protected Double topK;

    public AiLlmEndpointParamsGoogleBuilder type(AiLlmEndpointParamsGoogleTypeField type) {
      this.type = new EnumWrapper<AiLlmEndpointParamsGoogleTypeField>(type);
      return this;
    }

    public AiLlmEndpointParamsGoogleBuilder type(
        EnumWrapper<AiLlmEndpointParamsGoogleTypeField> type) {
      this.type = type;
      return this;
    }

    public AiLlmEndpointParamsGoogleBuilder temperature(Double temperature) {
      this.temperature = temperature;
      return this;
    }

    public AiLlmEndpointParamsGoogleBuilder topP(Double topP) {
      this.topP = topP;
      return this;
    }

    public AiLlmEndpointParamsGoogleBuilder topK(Double topK) {
      this.topK = topK;
      return this;
    }

    public AiLlmEndpointParamsGoogle build() {
      return new AiLlmEndpointParamsGoogle(this);
    }
  }
}
