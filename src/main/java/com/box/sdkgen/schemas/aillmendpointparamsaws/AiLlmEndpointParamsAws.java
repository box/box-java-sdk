package com.box.sdkgen.schemas.aillmendpointparamsaws;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class AiLlmEndpointParamsAws extends SerializableObject {

  @JsonDeserialize(
      using = AiLlmEndpointParamsAwsTypeField.AiLlmEndpointParamsAwsTypeFieldDeserializer.class)
  @JsonSerialize(
      using = AiLlmEndpointParamsAwsTypeField.AiLlmEndpointParamsAwsTypeFieldSerializer.class)
  protected EnumWrapper<AiLlmEndpointParamsAwsTypeField> type;

  protected Double temperature;

  @JsonProperty("top_p")
  protected Double topP;

  public AiLlmEndpointParamsAws() {
    super();
    this.type =
        new EnumWrapper<AiLlmEndpointParamsAwsTypeField>(
            AiLlmEndpointParamsAwsTypeField.AWS_PARAMS);
  }

  protected AiLlmEndpointParamsAws(AiLlmEndpointParamsAwsBuilder builder) {
    super();
    this.type = builder.type;
    this.temperature = builder.temperature;
    this.topP = builder.topP;
  }

  public EnumWrapper<AiLlmEndpointParamsAwsTypeField> getType() {
    return type;
  }

  public Double getTemperature() {
    return temperature;
  }

  public Double getTopP() {
    return topP;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AiLlmEndpointParamsAws casted = (AiLlmEndpointParamsAws) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(temperature, casted.temperature)
        && Objects.equals(topP, casted.topP);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, temperature, topP);
  }

  @Override
  public String toString() {
    return "AiLlmEndpointParamsAws{"
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
        + "}";
  }

  public static class AiLlmEndpointParamsAwsBuilder {

    protected EnumWrapper<AiLlmEndpointParamsAwsTypeField> type;

    protected Double temperature;

    protected Double topP;

    public AiLlmEndpointParamsAwsBuilder type(AiLlmEndpointParamsAwsTypeField type) {
      this.type = new EnumWrapper<AiLlmEndpointParamsAwsTypeField>(type);
      return this;
    }

    public AiLlmEndpointParamsAwsBuilder type(EnumWrapper<AiLlmEndpointParamsAwsTypeField> type) {
      this.type = type;
      return this;
    }

    public AiLlmEndpointParamsAwsBuilder temperature(Double temperature) {
      this.temperature = temperature;
      return this;
    }

    public AiLlmEndpointParamsAwsBuilder topP(Double topP) {
      this.topP = topP;
      return this;
    }

    public AiLlmEndpointParamsAws build() {
      return new AiLlmEndpointParamsAws(this);
    }
  }
}
