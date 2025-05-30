package com.box.sdkgen.schemas.aillmendpointparamsibm;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class AiLlmEndpointParamsIbm extends SerializableObject {

  @JsonDeserialize(
      using = AiLlmEndpointParamsIbmTypeField.AiLlmEndpointParamsIbmTypeFieldDeserializer.class)
  @JsonSerialize(
      using = AiLlmEndpointParamsIbmTypeField.AiLlmEndpointParamsIbmTypeFieldSerializer.class)
  protected EnumWrapper<AiLlmEndpointParamsIbmTypeField> type;

  protected Double temperature;

  @JsonProperty("top_p")
  protected Double topP;

  @JsonProperty("top_k")
  protected Double topK;

  public AiLlmEndpointParamsIbm() {
    super();
    this.type =
        new EnumWrapper<AiLlmEndpointParamsIbmTypeField>(
            AiLlmEndpointParamsIbmTypeField.IBM_PARAMS);
  }

  protected AiLlmEndpointParamsIbm(AiLlmEndpointParamsIbmBuilder builder) {
    super();
    this.type = builder.type;
    this.temperature = builder.temperature;
    this.topP = builder.topP;
    this.topK = builder.topK;
  }

  public EnumWrapper<AiLlmEndpointParamsIbmTypeField> getType() {
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
    AiLlmEndpointParamsIbm casted = (AiLlmEndpointParamsIbm) o;
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
    return "AiLlmEndpointParamsIbm{"
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

  public static class AiLlmEndpointParamsIbmBuilder {

    protected EnumWrapper<AiLlmEndpointParamsIbmTypeField> type;

    protected Double temperature;

    protected Double topP;

    protected Double topK;

    public AiLlmEndpointParamsIbmBuilder type(AiLlmEndpointParamsIbmTypeField type) {
      this.type = new EnumWrapper<AiLlmEndpointParamsIbmTypeField>(type);
      return this;
    }

    public AiLlmEndpointParamsIbmBuilder type(EnumWrapper<AiLlmEndpointParamsIbmTypeField> type) {
      this.type = type;
      return this;
    }

    public AiLlmEndpointParamsIbmBuilder temperature(Double temperature) {
      this.temperature = temperature;
      return this;
    }

    public AiLlmEndpointParamsIbmBuilder topP(Double topP) {
      this.topP = topP;
      return this;
    }

    public AiLlmEndpointParamsIbmBuilder topK(Double topK) {
      this.topK = topK;
      return this;
    }

    public AiLlmEndpointParamsIbm build() {
      return new AiLlmEndpointParamsIbm(this);
    }
  }
}
